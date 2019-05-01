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

import com.neu.edu.dao.AdminDao;
import com.neu.edu.dao.UserDao;
import com.neu.edu.pojo.Customer;
import com.neu.edu.pojo.Employee;
import com.neu.edu.validator.AddEmployeeValidator;
import com.neu.edu.validator.CustomerRegistrationValidator;

@Controller
@RequestMapping("/employeeRegister.htm")
public class EmployeeController {

	@Autowired
	AddEmployeeValidator addEmployeeValidator;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(addEmployeeValidator);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String formView(ModelMap model, Employee employee) throws IllegalStateException, IOException{
		
		model.addAttribute("employee", employee);
		return "Employee/add-admin";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String successView(@Validated @ModelAttribute("employee") Employee employee, BindingResult bindingResult, ModelMap model, AdminDao adminDao, HttpServletRequest request) {
		if (bindingResult.hasErrors()) {
			return "Employee/add-admin";
		}
		try {
			int result = adminDao.registerEmployee(employee);
			if(result == 1) {
				Email email = new SimpleEmail();
				email.setHostName("smtp.googlemail.com");
				email.setSmtpPort(465);
				email.setAuthenticator(new DefaultAuthenticator("finalproj94@gmail.com", "lionelmessi10"));
				email.setSSLOnConnect(true);
				email.setFrom("finalproj94@gmail.comm");
				email.setSubject("Admin Registration");
				email.setMsg("Dear " +employee.getEmpName()+","+"\n\nYou have successfully registered as Admin. Please visit the site to open Account"+"\n\nPlease find below the credentials:"+"\nAdminID - "+employee.getEmployeeId()+"\nAdmin Password - "+employee.getPassword()+"\n\nThanks,"+"\nOnlineBanking");
				email.addTo(employee.getEmailId());
				email.send();
				return "home";
			}
		}catch(Exception ex) {
			System.out.println("Error Encountered");
		}
		HttpSession session = request.getSession();
		String error = "Employee can not be registered at this point of time";
		session.setAttribute("error",error);
		return "access-Denied";
	}
}
