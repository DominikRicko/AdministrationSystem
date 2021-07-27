package com.samuraiDigital.adminsystem.api.user;

import java.util.Collection;
import java.util.Optional;

public interface UserResourceService {

	Collection<UserResource> getUsers();
	Optional<UserResource> getUser(Integer id);
	Optional<UserResource> saveUser(UserResource user);
	Optional<UserResource> updateUser(Integer id, UserResource user);
	Boolean deleteUserById(Integer id);
	
}
