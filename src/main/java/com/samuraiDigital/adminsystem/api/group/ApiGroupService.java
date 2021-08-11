package com.samuraiDigital.adminsystem.api.group;

import java.util.Collection;

public interface ApiGroupService {

	Collection<ApiGroupResource> getGroups();

	ApiGroupResource getGroup(Integer id);

	ApiGroupResource createGroup(ApiGroupResource group);

	ApiGroupResource updateGroup(Integer id, ApiGroupResource newGroup);

	void deleteGroup(Integer id);

}
