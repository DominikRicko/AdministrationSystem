package com.samuraiDigital.adminsystem.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class GroupsController {

	@RequestMapping(method = RequestMethod.GET, path = "/groups")
	public String getGroups() {
		return "pages/groups";
	}

}
