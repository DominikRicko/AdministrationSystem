package com.samuraiDigital.adminsystem.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

	@RequestMapping({"/login","/login.html"})
	public String getLogin() {
		return "login";
	}
	
}
