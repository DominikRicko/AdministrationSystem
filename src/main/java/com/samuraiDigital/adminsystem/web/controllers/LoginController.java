package com.samuraiDigital.adminsystem.web.controllers;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

	@RequestMapping("/login")
	public String getLogin(Principal principal) {

		if (principal != null) {
			return "redirect:/index";
		}

		return "pages/login";
	}

}
