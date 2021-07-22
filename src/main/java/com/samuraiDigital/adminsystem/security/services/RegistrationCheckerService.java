package com.samuraiDigital.adminsystem.security.services;

import com.samuraiDigital.adminsystem.security.model.UserCredentials;

public interface RegistrationCheckerService {
	public boolean Check(UserCredentials userCredentials);
}
