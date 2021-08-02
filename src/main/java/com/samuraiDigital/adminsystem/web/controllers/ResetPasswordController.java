package com.samuraiDigital.adminsystem.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.samuraiDigital.adminsystem.web.services.PasswordResetService;

@Controller
public class ResetPasswordController {

	private PasswordResetService passwordResetService;

	public ResetPasswordController(PasswordResetService passwordResetService) {
		super();
		this.passwordResetService = passwordResetService;
	}

	@RequestMapping(value = "/reset_password", method = RequestMethod.GET)
	public String getPasswordReset() {
		return "pages/reset_password";
	}

	@RequestMapping(value = "/reset_password", method = RequestMethod.POST)
	public String startPasswordReset(@RequestParam("email") String email) {

		passwordResetService.requestPasswordReset(email);

		return "pages/login";
	}

}
