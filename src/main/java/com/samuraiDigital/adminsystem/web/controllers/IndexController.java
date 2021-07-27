package com.samuraiDigital.adminsystem.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

	@RequestMapping({"/","","/index"})
	public String index() {
		return "/pages/index";
	}
	
	@RequestMapping({"/users"})
	public String users() {
		return "/pages/users";
	}
	
}
