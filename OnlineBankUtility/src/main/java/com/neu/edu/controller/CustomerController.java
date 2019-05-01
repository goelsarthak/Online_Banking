package com.neu.edu.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.neu.edu.dao.UserDao;
import com.neu.edu.exception.UserException;
import com.neu.edu.pojo.Customer;

@Controller
public class CustomerController {

	@RequestMapping(value="/updateCustomerInfo.htm", method = RequestMethod.GET)
	public ModelAndView getView(HttpServletRequest request ) {
		HttpSession session = request.getSession();
		Customer customer = (Customer) session.getAttribute("customer");
		return new ModelAndView("Customer/update-details", "customer", customer);
	}
	
	@RequestMapping(value="/updateCustInfo.htm", method= RequestMethod.POST)
	public String updateView(@RequestParam("email") String email, @RequestParam("address") String address, @RequestParam("phone") String phone, HttpServletRequest request, UserDao userDao) throws UserException {
		HttpSession session = request.getSession();
		Customer customer = (Customer) session.getAttribute("customer");
		int result = userDao.updateCustomerDetails(customer, address, email, phone);
		if(result == 1) {
			return "redirect:/updateCustInfo.htm";
		}
		return "access-Denied";
	}
	
	@RequestMapping(value="/updateCustInfo.htm", method= RequestMethod.GET)
	public ModelAndView getupdateView(HttpServletRequest request, UserDao userDao) {
		HttpSession session = request.getSession();
		Customer customer = (Customer) session.getAttribute("customer");
		return new ModelAndView("Customer/login-success","customer",customer);
	}
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView goToHome(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Customer customer = (Customer) session.getAttribute("customer");
		return new ModelAndView("Customer/login-success", "customer", customer);
	}
}
