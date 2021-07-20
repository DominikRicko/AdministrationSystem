package com.samuraiDigital.adminsystem.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

	@RequestMapping("/login")
	public String getLogin() {
		return "login";
	}
	
	@RequestMapping("/register")
	public String getRegister() {
		return "register";
	}
}