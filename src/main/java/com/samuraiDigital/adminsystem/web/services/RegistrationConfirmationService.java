package com.samuraiDigital.adminsystem.web.services;

import com.samuraiDigital.adminsystem.data.model.UserSecurityDetails;

public interface RegistrationConfirmationService {

	void requestConfirmation(UserSecurityDetails user);

	void confirmRegistration(UserSecurityDetails user);

}
