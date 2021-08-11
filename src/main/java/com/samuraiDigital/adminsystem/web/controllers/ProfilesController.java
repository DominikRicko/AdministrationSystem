package com.samuraiDigital.adminsystem.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ProfilesController {

	@RequestMapping(method = RequestMethod.GET, value = "/profile")
	public String getProfile() {
		return "/pages/profile";
	}

}
