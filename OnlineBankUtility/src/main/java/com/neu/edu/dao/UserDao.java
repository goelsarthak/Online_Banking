package com.neu.edu.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.neu.edu.exception.BankException;
import com.neu.edu.exception.UserException;
import com.neu.edu.pojo.AccountDetails;
import com.neu.edu.pojo.Customer;
import com.neu.edu.pojo.Employee;
import com.neu.edu.pojo.Role;
import com.neu.edu.pojo.TransactionDetails;

public class UserDao extends Dao {

	public UserDao() {

	}

	public Customer authenticateCustomer(int id, String password) throws UserException {
		try {
			begin();
			Customer customer = getSession().get(Customer.class, id);
			if (customer.getCustomerPassword().equalsIgnoreCase(password)) {
				return customer;
			}
			commit();
		} catch (HibernateException ex) {
			rollback();
			throw new UserException("Exception while authenticating the user: " + ex.getMessage());
		}
		return null;
	}

	public int registerCustomer(Customer customer) throws UserException {
		try {
//			begin();
//			Role role = getSession().get(Role.class, 2);
//			commit();
//			customer.setRole(role);
			
			begin();
			getSession().save(customer);
			commit();
			return 1;
		} catch (HibernateException ex) {
			rollback();
			throw new UserException("Exception while registering the customer: " + ex.getMessage());
		} catch(Exception ex) {
			rollback();
		}
		return -1;
	}
	
	public int updateCustomerDetails(Customer customer, String address, String email, String phone) throws UserException {
		try {
			begin();
			Customer cust = getSession().get(Customer.class, customer.getCustomerID());
			commit();
			cust.setCustomerAddress(address);
			cust.setCustomerEmail(email);
			cust.setPhoneNumber(phone);
			begin();
			getSession().flush();
			commit();
			return 1;
		}catch(Exception ex) {
			rollback();
			throw new UserException("Exception while updating the deatils the customer: " + ex.getMessage());
		}
	}

}