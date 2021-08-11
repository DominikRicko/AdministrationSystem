package com.samuraiDigital.adminsystem.api.user;

import java.util.Collection;

public interface ApiUserService {

	Collection<ApiUserResource> getUsers();

	ApiUserResource getUser(Integer id);

	ApiUserResource saveUser(ApiUserResource user);

	ApiUserResource updateUser(Integer id, ApiUserResource user);

	void deleteUserById(Integer id);

}
