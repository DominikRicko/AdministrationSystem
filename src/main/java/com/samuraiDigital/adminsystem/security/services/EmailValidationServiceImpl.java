package com.samuraiDigital.adminsystem.security.services;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.stereotype.Service;

@Service
public class EmailValidationServiceImpl implements EmailValidationService {

	private EmailValidator validator = EmailValidator.getInstance();

	@Override
	public boolean IsValid(String email) {
		return validator.isValid(email);
	}

}
