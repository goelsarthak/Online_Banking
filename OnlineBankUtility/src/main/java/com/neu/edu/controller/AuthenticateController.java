package com.neu.edu.controller;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.neu.edu.dao.UserDao;
import com.neu.edu.pojo.Customer;
import com.neu.edu.pojo.Employee;

@Controller
public class AuthenticateController {
	
	@Autowired
	private ServletContext application;
	
	@RequestMapping(value = "/login.htm", method = RequestMethod.POST)
	public ModelAndView getView(@RequestParam("loginId") int id, @RequestParam("password") String password,HttpServletRequest request, UserDao userDao) {
		HttpSession session = request.getSession();
		Customer customer = null;
		try {
			customer = userDao.authenticateCustomer(id, password);
			if(customer!= null) {
				session.setAttribute("customer",customer);
				return new ModelAndView("Customer/login-success","customer",customer);
			}
		}catch(Exception ex) {
			System.out.println("Error Encountered");
		}
		
		String error="Credentials not Correct";
		session.invalidate();
		return new ModelAndView("access-Denied","error",error);
	}
	
}
