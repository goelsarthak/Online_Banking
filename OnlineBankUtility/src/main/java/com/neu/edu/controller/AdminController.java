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

import com.neu.edu.dao.AdminDao;
import com.neu.edu.pojo.AccountDetails;
import com.neu.edu.pojo.Employee;

@Controller
public class AdminController {

	@RequestMapping(value="/emplogin.htm", method= RequestMethod.GET)
	public String getEmpView(HttpServletRequest request) {
		HttpSession session = request.getSession();
		return "Employee/employee-login";
	}
	
	@RequestMapping(value = "/EmpDashboard.htm", method = RequestMethod.POST)
	public ModelAndView getEmployeeLogin(@RequestParam("loginId") int id, @RequestParam("password") String password,HttpServletRequest request, AdminDao adminDao) {
		HttpSession session = request.getSession();
		Employee employee = null;
		try {
			employee = adminDao.authenticateEmployee(id, password);
			if(employee!= null) {
				session.setAttribute("employee",employee);
				return new ModelAndView("Employee/admin-dashboard","employee",employee);
			}
		}catch(Exception ex) {
			System.out.println("Error Encountered");
		}
		
		String error="Credentials not Correct";
		request.getSession().invalidate();
		return new ModelAndView("access-Denied","error",error);
	}
	
	@RequestMapping(value="/dormantAccounts.htm", method= RequestMethod.GET)
	public ModelAndView getDormantAccount(ModelMap model, HttpServletRequest request, AdminDao adminDao) {
		HttpSession session = request.getSession();
		List<AccountDetails> ad = adminDao.getDormantAccount();
		return new ModelAndView("Employee/dormant-account","ad",ad);
	}
	
	@RequestMapping(value = "/empHome", method = RequestMethod.GET)
	public ModelAndView goToHome(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Employee employee = (Employee) session.getAttribute("employee");
		return new ModelAndView("Employee/admin-dashboard", "employee", employee);
	}
	
	@RequestMapping(value="/minimumBalance.htm", method= RequestMethod.GET)
	public ModelAndView getMinBalanceAccount(ModelMap model, HttpServletRequest request, AdminDao adminDao) {
		HttpSession session = request.getSession();
		double minBalance = 10.0;
		List<AccountDetails> ad = adminDao.checkMinBal(minBalance);
		return new ModelAndView("Employee/dormant-account","ad",ad);
	}
	
}
