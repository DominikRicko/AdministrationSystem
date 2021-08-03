package com.samuraiDigital.adminsystem.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.samuraiDigital.adminsystem.data.model.UserSecurityDetails;
import com.samuraiDigital.adminsystem.security.model.UserCredentials;
import com.samuraiDigital.adminsystem.security.services.RegistrationCheckerService;
import com.samuraiDigital.adminsystem.security.services.RegistrationService;
import com.samuraiDigital.adminsystem.web.services.RegistrationConfirmationService;
import com.samuraiDigital.adminsystem.web.services.WebMessageService;

@Controller
public class RegistrationController {

	private RegistrationCheckerService registrationChecker;
	private RegistrationService registrationService;
	private RegistrationConfirmationService confirmationService;
	private WebMessageService errorService;
	private WebMessageService infoService;

	public RegistrationController(RegistrationCheckerService registrationChecker, RegistrationService registrationService, RegistrationConfirmationService confirmationService, WebMessageService errorService, WebMessageService infoService) {
		super();
		this.registrationChecker = registrationChecker;
		this.registrationService = registrationService;
		this.confirmationService = confirmationService;
		this.errorService = errorService;
		this.infoService = infoService;
	}

	@RequestMapping("/register")
	public String getRegister() {
		return "pages/register";
	}

	@PostMapping("/perform_register")
	public String register(Model model, @RequestParam("email") String email, @RequestParam("username") String username,
			@RequestParam("password") String password) {

		UserCredentials userCredentials = new UserCredentials(username, email, password);
		if (this.registrationChecker.Check(userCredentials)) {

			infoService.addMessageToModel(model, "Registration success, please check your email for confirmation link.");
			UserSecurityDetails user = registrationService.register(userCredentials);

			confirmationService.requestConfirmation(user);

			return "pages/login";
		} else {

			errorService.addMessageToModel(model, "Could not register account, either email is being used, username is taken, or database has lost connection.");

			return "pages/register";
		}

	}

}
