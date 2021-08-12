package com.samuraiDigital.adminsystem.api.user;

import java.util.Collection;

public interface ApiUserService {

	Collection<ApiUserResource> getUsers();

	ApiUserResource getUser(String id);

	ApiUserResource saveUser(ApiUserResource user);

	ApiUserResource updateUser(String id, ApiUserResource user);

	void deleteUserById(String id);

}
