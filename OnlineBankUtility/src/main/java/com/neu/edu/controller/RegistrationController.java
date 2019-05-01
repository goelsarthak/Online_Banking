package com.neu.edu.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

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
import org.springframework.web.multipart.MultipartFile;

import com.neu.edu.dao.UserDao;
import com.neu.edu.pojo.Customer;
import com.neu.edu.validator.CustomerRegistrationValidator;

@Controller
@RequestMapping("/register.htm")
public class RegistrationController {
	
	@Autowired
	CustomerRegistrationValidator customerRegistrationValidator;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(customerRegistrationValidator);
	}
	
	private String generateFileName(MultipartFile multiPart) {
        return new Date().getTime() + "-" + multiPart.getOriginalFilename().replace(" ", "_");
    }
	
	public String uploadFile(MultipartFile multipartFile) throws Exception {
        String fileName = generateFileName(multipartFile);
        String uploadDir = "uploads/";
        String filePath = uploadDir + fileName;
        try {
            File file = new File(filePath);
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(multipartFile.getBytes());
            fos.close();
        } catch (Exception e) {
            throw e;
        }

        return filePath;

    }
	
	@RequestMapping(method = RequestMethod.GET)
	public String formView(ModelMap model, Customer customer) throws IllegalStateException, IOException{
		
		model.addAttribute("customer", customer);
		return "Customer/register-user";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String successView(@Validated @ModelAttribute("customer") Customer customer, BindingResult bindingResult, ModelMap model, UserDao userDao, HttpServletRequest request) {
		if (bindingResult.hasErrors()) {
			return "Customer/register-user";  //the are validation errors, go to the form view
		}
		try {
			String localPath = "/Users/sarthakgoel/Desktop/";
			String fileName = generateFileName(customer.getPhoto());
			customer.getPhoto().transferTo(new File(localPath, fileName));
	        customer.setPhotoFile(localPath+fileName);
	        int result = userDao.registerCustomer(customer);
			if(result == 1) {
				Email email = new SimpleEmail();
				email.setHostName("smtp.googlemail.com");
				email.setSmtpPort(465);
				email.setAuthenticator(new DefaultAuthenticator("finalproj94@gmail.com", "lionelmessi10"));
				email.setSSLOnConnect(true);
				email.setFrom("finalproj94@gmail.comm");
				email.setSubject("Customer Registration");
				email.setMsg("Dear " +customer.getFirstName()+","+"\n\nYou have successfully registered as Customer. Please visit the site to open Account"+"\n\nPlease find below the credentials:"+"\nCustomerID - "+customer.getCustomerID()+"\nCustomer Password - "+customer.getCustomerPassword()+"\n\nThanks,"+"\nOnlineBanking");
				email.addTo(customer.getCustomerEmail());
				email.send();
				return "home";
			}
		}catch(Exception ex) {
			System.out.println("Error Encountered");
		}
		HttpSession session = request.getSession();
		String error = "EmailID is not correct or already in use";
		session.setAttribute("error",error);
		return "access-Denied";
		//no errors, so go to the success view
	}

}
