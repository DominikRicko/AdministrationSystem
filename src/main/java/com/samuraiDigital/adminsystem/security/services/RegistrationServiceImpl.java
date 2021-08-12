package com.samuraiDigital.adminsystem.security.services;

import java.time.ZonedDateTime;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.samuraiDigital.adminsystem.data.model.UserSecurityDetails;
import com.samuraiDigital.adminsystem.data.repositories.UserSecurityDetailsRepository;
import com.samuraiDigital.adminsystem.security.model.UserCredentials;

@Service
public class RegistrationServiceImpl implements RegistrationService {

	private UserSecurityDetailsRepository userRepository;
	private PasswordEncoder encoder;

	public RegistrationServiceImpl(UserSecurityDetailsRepository userRepository, PasswordEncoder encoder) {
		super();
		this.userRepository = userRepository;
		this.encoder = encoder;
	}

	@Override
	public UserSecurityDetails register(UserCredentials userCredentials) {

		UserSecurityDetails newUser = new UserSecurityDetails();

		newUser.setEmail(userCredentials.getEmail());
		newUser.setUsername(userCredentials.getUsername());
		newUser.setPasswordHash(encoder.encode(userCredentials.getPassword()));
		newUser.setEnabled(false);
		newUser.setAccountExpirationDate(ZonedDateTime.now().plusMonths(1));
		newUser.setCredentialsExpirationDate(ZonedDateTime.now().plusMonths(1));

		return userRepository.save(newUser);

	}

}