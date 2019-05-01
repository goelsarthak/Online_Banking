package com.neu.edu.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.neu.edu.exception.UserException;
import com.neu.edu.pojo.AccountDetails;
import com.neu.edu.pojo.Customer;
import com.neu.edu.pojo.Employee;
import com.neu.edu.pojo.Role;
import com.neu.edu.pojo.TransactionDetails;

public class AdminDao extends Dao {

	public int registerEmployee(Employee employee) throws UserException {
		try {
			
			begin();
			Role role = getSession().get(Role.class, 1);
			commit();
			employee.setRole(role);
			
			begin();
			getSession().save(employee);
			commit();
			return 1;
		} catch (HibernateException ex) {
			rollback();
			throw new UserException("Exception while registering the employee: " + ex.getMessage());
		} catch(Exception ex) {
			rollback();
		}
		return -1;
	}
	
	public Employee authenticateEmployee(int id, String password) {
		try {
			begin();
			Employee employee = getSession().get(Employee.class, id);
			commit();
			if (employee.getPassword().equals(password)) {
				return employee;
			}
		} catch (HibernateException ex) {
			rollback();
		}
		return null;
	}

	public List getDormantAccount() {
		List<TransactionDetails> transactions = new ArrayList<TransactionDetails>();
		try {
			begin();
			Query q = getSession().createQuery("from TransactionDetails");
			transactions = (List) q.list();
			commit();
		} catch (HibernateException ex) {
			rollback();
		}
		long diff = 0;
		Date tranDate = null;
		Date currentDate = new Date();
		HashSet<Integer> accNum = new HashSet<Integer>();

		for (TransactionDetails td : transactions) {
			tranDate = td.getDateTime();
			diff = currentDate.getTime() - tranDate.getTime();
			long diffHours = diff / (60 * 60 * 1000) % 24;
			// long diffDays = diff / (24 * 60 * 60 * 1000);

			if (diffHours > 1.0) {
				accNum.add(td.getAccountNumber());
			}
		}

		List<AccountDetails> ad = new ArrayList<AccountDetails>();
		for (int accountNumber : accNum) {
			begin();
			AccountDetails accDet = (AccountDetails) getSession().get(AccountDetails.class, accountNumber);
			commit();
			ad.add(accDet);
		}
		notifyDormantAccount(ad);
		return ad;
	}

	public List checkMinBal(double minBal) {
		List<AccountDetails> ad = new ArrayList<AccountDetails>();
		try {
			begin();
			Query q = getSession().createQuery("from AccountDetails where accountBalance <=:type");
			q.setParameter("type", minBal);
			ad = (List) q.list();
			commit();
			sendLowBalanceMailer(ad);
			return ad;
		} catch (HibernateException ex) {
			rollback();
		}
		return ad;
	}

	public void notifyDormantAccount(List<AccountDetails> list) {
		for (AccountDetails ad : list) {
			try {
				if (ad != null) {
					Email email = new SimpleEmail();
					email.setHostName("smtp.googlemail.com");
					email.setSmtpPort(465);
					email.setAuthenticator(new DefaultAuthenticator("finalproj94@gmail.com", "lionelmessi10"));
					email.setSSLOnConnect(true);
					email.setFrom("finalproj94@gmail.comm");
					email.setSubject("Dormant Account Alert");
					email.setMsg("Dear " + ad.getCustomer().getFirstName() + ","
							+ "\n\nYour Account has been deactivated because of its inactivity for long. Please visit the nearest bank branch to either re-activate or close."
							+ "\n\nPlease find below the account summary:" + "\nCustomer Name"
							+ ad.getCustomer().getFirstName() + "\nAccountNumber - " + ad.getAccountNumber()
							+ "\nAccount Balance - " + ad.getAccountBalance() + "\nDateOpened - " + ad.getDateOpened()
							+ "\n\nThanks," + "\nOnlineBanking");
					email.addTo(ad.getCustomer().getCustomerEmail());
					email.send();
				}
			} catch (EmailException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void sendLowBalanceMailer(List<AccountDetails> list) {
		for (AccountDetails ad : list) {
			try {
				Email email = new SimpleEmail();
				email.setHostName("smtp.googlemail.com");
				email.setSmtpPort(465);
				email.setAuthenticator(new DefaultAuthenticator("finalproj94@gmail.com", "lionelmessi10"));
				email.setSSLOnConnect(true);
				email.setFrom("finalproj94@gmail.comm");
				email.setSubject("URGENT | Low Account Balance");
				email.setMsg("Dear " + ad.getCustomer().getFirstName() + ","
						+ "\n\nYour account Balance has gone below the minimum limit. Please deposit money to keep getting the seemless experience"
						+ "\n\nPlease find below the account summary:" + "\nAccountNumber - " + ad.getAccountNumber()
						+ "\nAccount Balance - " + ad.getAccountBalance() + "\n\nThanks," + "\nOnlineBanking");
				email.addTo(ad.getCustomer().getCustomerEmail());
				email.send();
			} catch (EmailException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
