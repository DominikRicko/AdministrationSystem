package com.samuraiDigital.adminsystem.web.services;

import java.util.Collection;

import com.samuraiDigital.adminsystem.web.resources.UserResource;

public interface UserResourceService {

	Collection<UserResource> getUsers();
	Boolean saveUser(UserResource user);
	Boolean deleteUserById(Long id);
	
}
