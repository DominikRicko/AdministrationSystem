package com.samuraiDigital.adminsystem.api.group;

import java.util.Collection;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "Group", description = "Group's profile schema")
public class ApiGroupResource {

	@Schema(name = "id", type = "String")
	private String id;

	@Schema(name = "name", type = "String")
	private String name;

	@Schema(name = "members", type = "List of Strings")
	private Collection<String> members;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Collection<String> getMembers() {
		return members;
	}

	public void setMembers(Collection<String> members) {
		this.members = members;
	}

}
