package com.neu.edu.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.neu.edu.dao.AdminDao;
import com.neu.edu.pojo.Employee;

@Component
public class AddEmployeeValidator implements Validator {
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Employee.class.isAssignableFrom(clazz) || AdminDao.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "empName", "empty.empName", "Employee Name is Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phone", "empty.phone", "Phone is Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "emailId", "empty.emailId", "Email is Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "empty.password", "Password is Required");
	}
}
