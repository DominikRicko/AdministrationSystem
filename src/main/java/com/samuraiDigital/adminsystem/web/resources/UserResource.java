package com.samuraiDigital.adminsystem.web.resources;

import java.io.Serializable;
import java.sql.Date;
import java.util.Collection;

public class UserResource{
	
	final private Long id;
	final private String name;
	final private String surname;
	final private Date birthdate;
	final private Collection<String> groups;
	final private String email;
	final private Boolean enabled;
	final private Date account_expiration_date;
	final private Date credentials_expiration_date;
	
	public UserResource(Long id, String name, String surname, Date birthdate, Collection<String> groups, String email, Boolean enabled,
			Date account_expiration_date, Date credentials_expiration_date) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.birthdate = birthdate;
		this.groups = groups;
		this.email = email;
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

	public Date getBirthdate() {
		return birthdate;
	}

	public Collection<String> getGroups() {
		return groups;
	}

	public String getEmail() {
		return email;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public Date getAccount_expiration_date() {
		return account_expiration_date;
	}

	public Date getCredentials_expiration_date() {
		return credentials_expiration_date;
	}
	
}
