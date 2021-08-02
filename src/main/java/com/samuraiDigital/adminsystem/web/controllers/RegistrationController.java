package com.samuraiDigital.adminsystem.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.samuraiDigital.adminsystem.data.model.UserSecurityDetails;
import com.samuraiDigital.adminsystem.security.model.UserCredentials;
import com.samuraiDigital.adminsystem.security.services.RegistrationCheckerService;
import com.samuraiDigital.adminsystem.security.services.RegistrationService;
import com.samuraiDigital.adminsystem.web.services.RegistrationConfirmationService;

@Controller
public class RegistrationController {

	private RegistrationCheckerService registrationChecker;
	private RegistrationService registrationService;
	private RegistrationConfirmationService confirmationService;

	public RegistrationController(RegistrationCheckerService registrationChecker, RegistrationService registrationService, RegistrationConfirmationService confirmationService) {
		super();
		this.registrationChecker = registrationChecker;
		this.registrationService = registrationService;
		this.confirmationService = confirmationService;
	}

	@PostMapping("/perform_register")
	public String register(Model model, @RequestParam("email") String email, @RequestParam("username") String username,
			@RequestParam("password") String password) {

		UserCredentials userCredentials = new UserCredentials(username, email, password);
		if (this.registrationChecker.Check(userCredentials)) {

			model.addAttribute("info", "Registration success, check your email for confirmation link.");
			UserSecurityDetails user = registrationService.register(userCredentials);

			confirmationService.requestConfirmation(user);

			return "pages/login";
		} else {
			model.addAttribute("error", "Registration failed.");
			return "pages/register";
		}

	}

}
