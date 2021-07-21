package com.samuraiDigital.adminsystem.security.services;

import org.springframework.stereotype.Service;

import com.samuraiDigital.adminsystem.data.repositories.UserSecurityDetailsRepository;
import com.samuraiDigital.adminsystem.security.model.UserCredentials;

@Service
public class RegistrationCheckerServiceImpl implements RegistrationCheckerService{

	private UserSecurityDetailsRepository userRepository;
	private EmailValidationService emailValidator;
	
	public RegistrationCheckerServiceImpl(UserSecurityDetailsRepository userRepository, EmailValidationService emailValidator) {
		this.userRepository = userRepository;
		this.emailValidator = emailValidator;
	}

	@Override
	public boolean Check(UserCredentials userCredentials) {

		if (!emailValidator.IsValid(userCredentials.getEmail())) {
			return false;
		}
		if (userRepository.findByUsername(userCredentials.getUsername()).isPresent()) {
			return false;
		}
		if(userRepository.findByEmail(userCredentials.getEmail()).isPresent()){
			return false;
		}
		
		return true;
	}
	
}
