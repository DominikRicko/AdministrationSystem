package com.samuraiDigital.adminsystem.web.controllers;

import java.util.Collection;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

	@RequestMapping(method = RequestMethod.GET, path = "/getUsers")
	public ResponseEntity<Collection<UserResource>> getUsers() {
		return new ResponseEntity<>(resourceService.getUsers(), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.PUT ,path = "/createUser")
	public ResponseEntity<UserResource> createUser(UserResource user){
		
		if (resourceService.saveUser(user))
			return new ResponseEntity<>(user, HttpStatus.CREATED);
		else
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
			
	}
	
	@RequestMapping(method = RequestMethod.DELETE, path = "/removeUser")
	public ResponseEntity<UserResource> removeUser(UserResource user){
		
		resourceService.deleteUserById(user.getId());
		
		return new ResponseEntity<>(null, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "/updateUser")
	public ResponseEntity<UserResource> updateUser(UserResource user){
		
		if(resourceService.saveUser(user))
			return new ResponseEntity<>(user, HttpStatus.OK);
		else
			return new ResponseEntity<>(user, HttpStatus.BAD_REQUEST);
		
	}
	
}
