package com.samuraiDigital.adminsystem.api.profile;

import java.util.Collection;
import java.util.HashSet;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "Profile", description = "User's profile schema")
public class ApiProfileResource {

	@Schema(name = "id", type = "Integer", required = true)
	private Integer id;

	@Schema(name = "name", type = "String", required = true)
	private String name;

	@Schema(name = "surname", type = "String", required = true)
	private String surname;

	@Schema(name = "address", type = "String", required = false)
	private String address;

	@Schema(name = "birthdate", type = "Date", required = false)
	private String birthdate;

	@Schema(name = "email", type = "String", required = true)
	private String email;

	@Schema(name = "username", type = "String", required = true)
	private String username;

	@Schema(name = "account_expiration_date", type = "Date", required = true)
	private String account_expiration_date;

	@Schema(name = "credentials_expiration_date", type = "Date", required = true)
	private String credentials_expiration_date;

	@Schema(name = "enabled", type = "boolean", required = true)
	private Boolean enabled;

	@Schema(name = "privileges", type = "Collection of Strings", required = false)
	private Collection<String> privileges = new HashSet<>();

	@Schema(name = "groups", type = "Collection of Strings", required = false)
	private Collection<String> groups = new HashSet<>();

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

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAccount_expiration_date() {
		return account_expiration_date;
	}

	public void setAccount_expiration_date(String account_expiration_date) {
		this.account_expiration_date = account_expiration_date;
	}

	public String getCredentials_expiration_date() {
		return credentials_expiration_date;
	}

	public void setCredentials_expiration_date(String credentials_Expiration_date) {
		this.credentials_expiration_date = credentials_Expiration_date;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public void setPrivileges(Collection<String> privileges) {
		this.privileges = privileges;
	}

	public Collection<String> getPrivileges() {
		return privileges;
	}

	public void addPrivileges(String... privileges) {

		for (String privilege : privileges) {
			this.privileges.add(privilege);
		}

	}

	public void setGroups(Collection<String> groups) {
		this.groups = groups;
	}

	public Collection<String> getGroups() {
		return groups;
	}

	public void addGroups(String... groups) {

		for (String group : groups) {
			this.groups.add(group);
		}

	}


}
