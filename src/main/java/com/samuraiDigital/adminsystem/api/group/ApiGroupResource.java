package com.samuraiDigital.adminsystem.api.group;

import java.util.Collection;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "Group", description = "Group's profile schema")
public class ApiGroupResource {

	@Schema(name = "id", type = "Integer")
	private Integer id;

	@Schema(name = "name", type = "String")
	private String name;

	@Schema(name = "members", type = "List of Strings")
	private Collection<String> members;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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
