package com.samuraiDigital.adminsystem.web.services;

import com.samuraiDigital.adminsystem.data.model.UserSecurityDetails;

public interface PasswordResetService {

	void requestPasswordReset(String email);

	void resetPassword(UserSecurityDetails user, String password);

}
