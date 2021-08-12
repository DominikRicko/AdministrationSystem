package com.samuraiDigital.adminsystem.data.model;

import java.util.HashSet;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class SecurityGroup {

	@Id
	private String id;

	private String name;

	@DBRef(lazy = false)
	private Set<Authority> authorities = new HashSet<>();

	@DBRef(lazy = true)
	private Set<UserSecurityDetails> members = new HashSet<>();

	public SecurityGroup(String name) {
		super();
		this.name = name;
	}

	public SecurityGroup() {
		super();
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

	public Set<Authority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Set<Authority> authorities) {
		this.authorities = authorities;
	}

	public void addAuthority(Authority... authority) {
		this.authorities.addAll(authorities);
	}

	public void removeAuthority(Authority authority) {
		this.authorities.remove(authority);
	}

	public Set<UserSecurityDetails> getMembers() {
		return members;
	}

	public void setMembers(Set<UserSecurityDetails> members) {
		this.members = members;
	}

	public void addMember(UserSecurityDetails member) {
		this.members.add(member);
	}

	public void removeMember(UserSecurityDetails member) {
		this.members.add(member);
	}

	@Override
	public String toString() {
		return "SecurityGroup [id=" + ((id != null) ? (id) : "Unassigned") + ", name=" + name + "]";
	}

}
