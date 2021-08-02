package com.samuraiDigital.adminsystem.security.services;

import com.samuraiDigital.adminsystem.data.model.UserSecurityDetails;
import com.samuraiDigital.adminsystem.security.model.UserCredentials;

public interface RegistrationService {

	public UserSecurityDetails register(UserCredentials userCredentials);

}
