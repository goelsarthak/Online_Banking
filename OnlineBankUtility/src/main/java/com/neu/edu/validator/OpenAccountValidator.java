package com.neu.edu.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.neu.edu.dao.BankAccountDao;
import com.neu.edu.pojo.AccountDetails;
import com.neu.edu.pojo.Customer;

@Component
public class OpenAccountValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		// This Validator validates *just* User instances
		return AccountDetails.class.isAssignableFrom(clazz) || BankAccountDao.class.isAssignableFrom(clazz) || Customer.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// basic validations
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "accountBalance", "empty.accountBalance", "Min Balance of $50 is Required");
	}
}
