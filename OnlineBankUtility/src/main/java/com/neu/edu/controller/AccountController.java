package com.neu.edu.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.neu.edu.dao.BankAccountDao;
import com.neu.edu.pojo.AccountDetails;
import com.neu.edu.pojo.Customer;
import com.neu.edu.validator.OpenAccountValidator;

@Controller
@RequestMapping("/newAccount.htm")
public class AccountController {

	@Autowired
	OpenAccountValidator openAccountValidator;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(openAccountValidator);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String formView(ModelMap model, HttpServletRequest request) throws IllegalStateException, IOException {

		HttpSession session = request.getSession();
		Customer customer = (Customer) session.getAttribute("customer");
		AccountDetails accountDetails = new AccountDetails();
		model.addAttribute("accountDetails", accountDetails);
		return "Customer/new-account";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String successView(@Validated @ModelAttribute("accountDetails") AccountDetails accountDetails,
			BindingResult bindingResult, ModelMap model, HttpServletRequest request, BankAccountDao bankDao) {
		if (bindingResult.hasErrors()) {
			return "Customer/new-account"; // the are validation errors, go to the form view
		}
		try {
			HttpSession session = request.getSession();
			Customer customer = (Customer) session.getAttribute("customer");
			int result = bankDao.openCustomerBankAccount(customer, accountDetails);
			if (result == 1) {
				session.setAttribute("customer", customer);
				model.addAttribute("customer", customer);
				Email email = new SimpleEmail();
				email.setHostName("smtp.googlemail.com");
				email.setSmtpPort(465);
				email.setAuthenticator(new DefaultAuthenticator("finalproj94@gmail.com", "lionelmessi10"));
				email.setSSLOnConnect(true);
				email.setFrom("finalproj94@gmail.comm");
				email.setSubject("Customer Account");
				email.setMsg("Dear " +customer.getFirstName()+","+"\n\nYou have successfully opened a new Account" +"\n\nPlease find below the account information:"+"\n\nAccount Number: "+accountDetails.getAccountNumber()+"\nAccount Type: "+accountDetails.getAccountType()+"\nAccount Balance: "+accountDetails.getAccountBalance()+"\n\nThanks,\nOnlineBankUtility");
				email.addTo(customer.getCustomerEmail());
				email.send();
				return "Customer/login-success";
			}
		} catch (Exception ex) {
			System.out.println("Error Encountered");
		}
		return "Customer/new-account";
	}
}
