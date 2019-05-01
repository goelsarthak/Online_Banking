package com.neu.edu.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.neu.edu.dao.TransactionDao;
import com.neu.edu.pojo.Customer;
import com.neu.edu.pojo.TransactionDetails;

@Controller
public class TransactionController {

	@RequestMapping(value = "/downloadPDF", method = RequestMethod.GET)
	public ModelAndView downloadExcel(HttpServletRequest request) {
		// create some sample data
		HttpSession session = request.getSession();
		List<TransactionDetails> transactions = (List) session.getAttribute("transactionlist");

		// return a view which will be resolved by an excel view resolver
		return new ModelAndView("pdfView", "transactions", transactions);
	}
	
	@RequestMapping(value = "/retrieveTransactions.htm", method = RequestMethod.GET)
	public ModelAndView createTransactionView(ModelMap model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		Customer customer = (Customer) session.getAttribute("customer");
		return new ModelAndView("Customer/account-transaction", "customer", customer);
	}

	@RequestMapping(value = "/getTransactions.htm", method = RequestMethod.POST)
	public ModelAndView getTransactionView(@RequestParam("FromAccNum") int fromAccNum, HttpServletRequest request,
			TransactionDao transactionDao) {
		HttpSession session = request.getSession();
		List<TransactionDetails> results = transactionDao.getCustomerTransactions(fromAccNum);
		if (results != null) {
			session.setAttribute("transactionlist", results);
			return new ModelAndView("Customer/get-transaction", "results", results);
		}
		Customer customer = (Customer) session.getAttribute("customer");
		return new ModelAndView("Customer/account-transaction", "customer", customer);
	}
}
