package com.samuraiDigital.adminsystem.api.group;

import java.util.Collection;

public interface ApiGroupService {

	Collection<ApiGroupResource> getGroups();

	ApiGroupResource getGroup(String id);

	ApiGroupResource createGroup(ApiGroupResource group);

	ApiGroupResource updateGroup(String id, ApiGroupResource newGroup);

	void deleteGroup(String id);

}
