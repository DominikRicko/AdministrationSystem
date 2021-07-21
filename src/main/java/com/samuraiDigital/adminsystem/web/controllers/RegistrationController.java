package com.samuraiDigital.adminsystem.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.samuraiDigital.adminsystem.security.model.UserCredentials;
import com.samuraiDigital.adminsystem.security.services.RegistrationCheckerService;

@Controller
public class RegistrationController {
	
	private RegistrationCheckerService registrationChecker;
	
	public RegistrationController(RegistrationCheckerService registrationChecker) {
		super();
		this.registrationChecker = registrationChecker;
	}

	@PostMapping("/perform_register")
	public String register(Model model,
			@RequestParam("email") String email, 
			@RequestParam("username") String username, 
			@RequestParam("password") String password) {
		
		UserCredentials userCredentials = new UserCredentials(username, email, password);
		if(this.registrationChecker.Check(userCredentials)) {
			model.addAttribute("info", "Registration success, check your email for confirmation link.");
			//registration process goes here.
			return "pages/login";
		}
		else {
			model.addAttribute("error", "Registration failed.");
			return "pages/register";
		}
		
	}
	
}
