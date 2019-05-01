package com.neu.edu.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.neu.edu.dao.UserDao;
import com.neu.edu.pojo.Customer;

@Component
public class CustomerRegistrationValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		//This Validator validates *just* User instances
		return Customer.class.isAssignableFrom(clazz) || UserDao.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		//basic validations
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "empty.first", "First Name is Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "empty.last", "Last Name is Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "customerAddress", "empty.last", "Address is Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phoneNumber", "empty.last", "Phone Number is Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "customerEmail", "empty.last", "Email is Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "customerPassword", "empty.last", "Password is Required");
		
		//add additional custom validations below
	}
}
