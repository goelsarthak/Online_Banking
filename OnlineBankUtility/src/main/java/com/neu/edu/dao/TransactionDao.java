package com.neu.edu.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;

import com.neu.edu.pojo.TransactionDetails;

public class TransactionDao extends Dao {

	public List getCustomerTransactions(int fromAccNum) {
		List<TransactionDetails> transactions = new ArrayList<TransactionDetails>();
		try {
			begin();
			Criteria cr = getSession().createCriteria(TransactionDetails.class);
			cr.add(Restrictions.eq("accountNumber", fromAccNum));
			transactions = cr.list();
			commit();
		}catch(HibernateException ex) {
			rollback();
		}
		return transactions;
	}
}
