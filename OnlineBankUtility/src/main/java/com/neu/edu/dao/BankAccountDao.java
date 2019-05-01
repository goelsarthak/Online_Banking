package com.neu.edu.dao;

import java.util.Date;
import java.util.List;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.hibernate.HibernateException;

import com.neu.edu.exception.BankException;
import com.neu.edu.pojo.AccountDetails;
import com.neu.edu.pojo.Customer;
import com.neu.edu.pojo.TransactionDetails;

public class BankAccountDao extends Dao {

	public int openCustomerBankAccount(Customer customer, AccountDetails accountDetails) throws BankException {

		List<AccountDetails> accD = customer.getAccountDetails();
		accountDetails.setDateOpened(new Date());
		accountDetails.setCustomer(customer);
		accD.add(accountDetails);
		customer.setAccountDetails(accD);
		try {
			begin();
			getSession().saveOrUpdate(customer);
			commit();
			return 1;
		} catch (HibernateException ex) {
			rollback();
			throw new BankException("Exception while opening a new bank account: " + ex.getMessage());
		}
	}

	public int transferWithIn(Customer customer, int toAccNum, int fromAccNum, double amount) throws BankException {
		List<TransactionDetails> transactions = customer.getTransaction();
		TransactionDetails tran1 = new TransactionDetails();
		tran1.setTransactionType("Credit");
		tran1.setAccountNumber(toAccNum);
		tran1.setAmount(amount);
		tran1.setDateTime(new Date());
		tran1.setCustomer(customer);

		TransactionDetails tran2 = new TransactionDetails();
		tran2.setTransactionType("Debit");
		tran2.setAccountNumber(fromAccNum);
		tran2.setAmount(amount);
		tran2.setDateTime(new Date());
		tran2.setCustomer(customer);

		transactions.add(tran1);
		transactions.add(tran2);

		customer.setTransaction(transactions);
		try {
			begin();
			getSession().saveOrUpdate(customer);
			commit();
			boolean result = reflectAccountChanges(fromAccNum, toAccNum, amount, customer);
			if (result == false) {
				throw new HibernateException("Can not be performed");
			}
			sendDebitMailer(customer, tran2);
			return 1;
		} catch (HibernateException ex) {
			rollback();
			throw new BankException("Exception while transferring funds within bank account: " + ex.getMessage());
		}
	}

	public boolean reflectAccountChanges(int fromBankAccount, int toBankAccount, double amount, Customer customer) {
		List<AccountDetails> accDetails = customer.getAccountDetails();
		boolean result = false;
		for (AccountDetails ac : accDetails) {
			if (ac.getAccountNumber() == fromBankAccount) {
				ac.setAccountBalance(ac.getAccountBalance() - amount);
				try {
					begin();
					getSession().saveOrUpdate(ac);
					commit();
					result = true;
				} catch (HibernateException ex) {
					result = false;
					rollback();
				}
				break;
			}
		}

		try {
			begin();
			AccountDetails beneficiary = (AccountDetails) getSession().get(AccountDetails.class, toBankAccount);
			if (beneficiary != null) {
				beneficiary.setAccountBalance(beneficiary.getAccountBalance() + amount);
				getSession().saveOrUpdate(beneficiary);
			}
			commit();

			result = true;
		} catch (HibernateException ex) {
			result = false;
			rollback();
		}
		return result;
	}

	public int transferIntra(Customer customer, int toAccNum, int fromAccNum, double amount) throws BankException {
		List<TransactionDetails> transactions = customer.getTransaction();
		TransactionDetails tran1 = new TransactionDetails();
		tran1.setTransactionType("Debit");
		tran1.setAccountNumber(fromAccNum);
		tran1.setAmount(amount);
		tran1.setDateTime(new Date());
		tran1.setCustomer(customer);

		transactions.add(tran1);
		customer.setTransaction(transactions);
		
		try {
			begin();
			getSession().saveOrUpdate(customer);
			commit();
			boolean result = reflectAccountChanges(fromAccNum, toAccNum, amount, customer);
			if (result == false) {
				throw new HibernateException("Can not be performed");
			}
			sendDebitMailer(customer, tran1);
		} catch (HibernateException ex) {
			rollback();
		}
		
		
		try {
			begin();
			AccountDetails beneficiary = (AccountDetails) getSession().get(AccountDetails.class, toAccNum);
			if (beneficiary != null) {
				Customer ben = beneficiary.getCustomer();
				List<TransactionDetails> list = ben.getTransaction();
				TransactionDetails tran2 = new TransactionDetails();
				tran2.setTransactionType("Credit");
				tran2.setAccountNumber(toAccNum);
				tran2.setAmount(amount);
				tran2.setDateTime(new Date());
				tran2.setCustomer(beneficiary.getCustomer());
				
				list.add(tran2);
				ben.setTransaction(list);
				
				getSession().saveOrUpdate(ben);
				commit();
				
				sendCreditMailer(ben, tran2);
			}
			
			return 1;
		} catch (HibernateException ex) {
			rollback();
			throw new BankException("Exception while transferring funds to other bank : " + ex.getMessage());
		}
	}
	
	public void sendCreditMailer(Customer customer, TransactionDetails td) {
		try {
			Email email = new SimpleEmail();
			email.setHostName("smtp.googlemail.com");
			email.setSmtpPort(465);
			email.setAuthenticator(new DefaultAuthenticator("finalproj94@gmail.com", "lionelmessi10"));
			email.setSSLOnConnect(true);
			email.setFrom("finalproj94@gmail.comm");
			email.setSubject("Credit Recieved");
			email.setMsg("Dear " + customer.getFirstName() + "," + "\n\nYour account has been credetied a transfer of $"
					+ td.getAmount() + "\n\nPlease find below the Transaction Details:" + "\n\nTransactionID: "
					+ td.getTransactionId() + "\nAccount Number: " + td.getAccountNumber() + "\nTransaction Type: "
					+ td.getTransactionType() + "\nTransaction Date" + td.getDateTime()
					+ "\n\nThanks,\nOnlineBankUtility");
			email.addTo(customer.getCustomerEmail());
			email.send();
		} catch (EmailException e) {
			e.printStackTrace();
		}
	}

	public void sendDebitMailer(Customer customer, TransactionDetails td) {
		try {
			Email email = new SimpleEmail();
			email.setHostName("smtp.googlemail.com");
			email.setSmtpPort(465);
			email.setAuthenticator(new DefaultAuthenticator("finalproj94@gmail.com", "lionelmessi10"));
			email.setSSLOnConnect(true);
			email.setFrom("finalproj94@gmail.comm");
			email.setSubject("Debit Request");
			email.setMsg("Dear " + customer.getFirstName() + "," + "\n\nYou have initiated a transfer of $"
					+ td.getAmount() + "\n\nPlease find below the Transaction Details:" + "\n\nTransactionID: "
					+ td.getTransactionId() + "\nAccount Number: " + td.getAccountNumber() + "\nTransaction Type: "
					+ td.getTransactionType() + "\nTransaction Date" + td.getDateTime()
					+ "\n\nThanks,\nOnlineBankUtility");
			email.addTo(customer.getCustomerEmail());
			email.send();
		} catch (EmailException e) {
			e.printStackTrace();
		}
	}
}
