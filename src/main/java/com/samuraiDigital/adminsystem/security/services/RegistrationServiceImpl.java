package com.samuraiDigital.adminsystem.security.services;

import java.time.LocalDateTime;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.samuraiDigital.adminsystem.data.model.UserSecurityDetails;
import com.samuraiDigital.adminsystem.data.repositories.UserSecurityDetailsRepository;
import com.samuraiDigital.adminsystem.security.model.UserCredentials;

@Service
public class RegistrationServiceImpl implements RegistrationService{

	private UserSecurityDetailsRepository userRepository;
	private PasswordEncoder encoder;
	
	public RegistrationServiceImpl(UserSecurityDetailsRepository userRepository, PasswordEncoder encoder) {
		super();
		this.userRepository = userRepository;
		this.encoder = encoder;
	}

	@Override
	public void register(UserCredentials userCredentials) {
		
		UserSecurityDetails newUser = new UserSecurityDetails();
    
		//TODO: change enabled to false after email sending service is done.
		newUser.setEmail(userCredentials.getEmail());
		newUser.setUsername(userCredentials.getUsername());
		newUser.setPasswordHash(encoder.encode(userCredentials.getPassword()));
		newUser.setEnabled(true);
		newUser.setAccountExpirationDate(LocalDateTime.now().plusDays(30).toLocalDate());
		newUser.setCredentialsExpirationDate(LocalDateTime.now().plusDays(30).toLocalDate());
		
		userRepository.save(newUser);
		
	}
	
}