package com.neu.edu.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.neu.edu.dao.BankAccountDao;
import com.neu.edu.pojo.Customer;

@Controller
public class ManageFundsController {

	@RequestMapping(value = "/transferwithinAccount.htm", method = RequestMethod.GET)
	public ModelAndView formView(ModelMap model, HttpServletRequest request) throws IllegalStateException, IOException {
		HttpSession session = request.getSession();
		Customer customer = (Customer) session.getAttribute("customer");
		return new ModelAndView("Customer/transfer-within-bank", "customer", customer);
	}

	@RequestMapping(value = "/transferAmount.htm")
	public ModelAndView getSuccessView(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Customer customer = (Customer)session.getAttribute("customer");
		return new ModelAndView("Customer/login-success", "customer", customer);
	}
	
	@RequestMapping(value = "/transferAmount.htm", method = RequestMethod.POST)
	public String successView(@RequestParam("FromAccNum") int fromAccNum, @RequestParam("ToAccNum") int toAccNum,
			@RequestParam("amount") double amount, HttpServletRequest request, BankAccountDao bankDao) {
		HttpSession session = request.getSession();
		if (amount >= 1 && fromAccNum != toAccNum) {
			Customer customer = (Customer) session.getAttribute("customer");
			try {
				int result = bankDao.transferWithIn(customer, toAccNum, fromAccNum, amount);
				if (result == 1) {
					session.setAttribute("customer", customer);
					return "redirect:/transferAmount.htm";
				}
			} catch (Exception ex) {
			}
		}
		String error = "Minimum Amount is $1 and Both Accounts can not be same";
		session.setAttribute("error",error);
		return "access-Denied";
	}
	
	@RequestMapping(value = "/transferIntraBank.htm")
	public ModelAndView getView(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Customer customer = (Customer)session.getAttribute("customer");
		return new ModelAndView("Customer/login-success", "customer", customer);
	}
	
	@RequestMapping(value = "/transferIntraBank.htm", method = RequestMethod.POST)
	public String transferIntraFunds(@RequestParam("FromAccNum") int fromAccNum, @RequestParam("ToAccNum") int toAccNum,
			@RequestParam("amount") double amount, HttpServletRequest request, BankAccountDao bankDao) {
		HttpSession session = request.getSession();
		if (amount >= 1 && fromAccNum != toAccNum) {
			Customer customer = (Customer) session.getAttribute("customer");
			try {
				int result = bankDao.transferIntra(customer, toAccNum, fromAccNum, amount);
				if (result == 1) {
					session.setAttribute("customer", customer);
					return "redirect:/transferIntraBank.htm";
				}
			} catch (Exception ex) {

			}
		}
		String error = "Minimum Amount is $1 and Both Accounts can not be same";
		session.setAttribute("error",error);
		return "access-Denied";
	}

	@RequestMapping(value = "/transferoutAccount.htm", method = RequestMethod.GET)
	public ModelAndView createView(ModelMap model, HttpServletRequest request)
			throws IllegalStateException, IOException {
		HttpSession session = request.getSession();
		Customer customer = (Customer) session.getAttribute("customer");
		return new ModelAndView("Customer/transfer-other-bank", "customer", customer);
	}
}
