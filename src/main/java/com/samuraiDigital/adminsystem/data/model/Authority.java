package com.samuraiDigital.adminsystem.data.model;

import java.util.HashSet;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;

@Document
public class Authority implements GrantedAuthority {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private String name;

	private String description;

	@DBRef(lazy = true)
	private Set<SecurityGroup> groups = new HashSet<SecurityGroup>();

	public Authority() {
		super();
	}

	public Authority(String name, String description) {
		super();
		this.name = name;
		this.description = description;
	}

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<SecurityGroup> getGroups() {
		return groups;
	}

	public void setGroups(Set<SecurityGroup> groups) {
		this.groups = groups;
	}

	public void addGroup(SecurityGroup group) {
		this.groups.add(group);
	}

	public void removeGroup(SecurityGroup group) {
		this.groups.remove(group);
	}

	@Override
	public String getAuthority() {
		return name;
	}

	@Override
	public String toString() {
		return "Authority [id=" + ((id != null) ? (id) : "Unassigned") + ", name=" + name + ", description="
				+ description + "]";
	}

}
