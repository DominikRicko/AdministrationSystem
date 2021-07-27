package com.samuraiDigital.adminsystem.web.resources;

import java.util.Collection;

public class UserResource{
	
	final private Long id;
	final private String name;
	final private String surname;
	final private String birthdate;
	final private Collection<String> groups;
	final private String email;
	final private String username;
	final private Boolean enabled;
	final private String account_expiration_date;
	final private String credentials_expiration_date;
	
	public UserResource(Long id, String name, String surname, String birthdate, Collection<String> groups, String email, String username, Boolean enabled,
			String account_expiration_date, String credentials_expiration_date) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.birthdate = birthdate;
		this.groups = groups;
		this.email = email;
		this.username = username;
		this.enabled = enabled;
		this.account_expiration_date = account_expiration_date;
		this.credentials_expiration_date = credentials_expiration_date;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}

	public String getBirthdate() {
		return birthdate;
	}

	public String getGroups() {
		
		StringBuilder builder = new StringBuilder();
		
		groups.forEach(it -> builder.append(it).append("\n"));
		
		return builder.toString();
	}

	public String getEmail() {
		return email;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public String getAccount_expiration_date() {
		return account_expiration_date;
	}

	public String getCredentials_expiration_date() {
		return credentials_expiration_date;
	}

	public String getUsername() {
		return username;
	}
	
}
