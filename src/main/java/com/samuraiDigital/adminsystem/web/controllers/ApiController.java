package com.samuraiDigital.adminsystem.web.controllers;

import java.util.Collection;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.samuraiDigital.adminsystem.web.resources.UserResource;
import com.samuraiDigital.adminsystem.web.services.UserResourceService;

@RestController
public class ApiController {
	
	private UserResourceService resourceService;
	
	public ApiController(UserResourceService resourceService) {
		super();
		this.resourceService = resourceService;
	}

	@RequestMapping("/getUsers")
	public Collection<UserResource> getUsers() {
		
		return resourceService.getUsers();
		
	}
	
}
