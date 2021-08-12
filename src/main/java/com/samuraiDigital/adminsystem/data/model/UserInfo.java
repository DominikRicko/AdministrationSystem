package com.samuraiDigital.adminsystem.data.model;

import java.time.ZonedDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class UserInfo {

	@Id
	private String id;
	private String name;
	private String surname;
	private ZonedDateTime birthdate;
	private String address;

	@DBRef(lazy = true)
	private UserSecurityDetails userSecurity = new UserSecurityDetails();

	public UserInfo() {
		super();
	}

	public UserInfo(String name, String surname, ZonedDateTime birthdate, String address) {
		super();
		this.name = name;
		this.surname = surname;
		this.birthdate = birthdate;
		this.address = address;
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

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public ZonedDateTime getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(ZonedDateTime birthdate) {
		this.birthdate = birthdate;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public UserSecurityDetails getUserSecurity() {
		return userSecurity;
	}

	public void setUserSecurity(UserSecurityDetails userSecurity) {
		this.userSecurity = userSecurity;
	}

	@Override
	public String toString() {
		return "UserInfo [id=" + ((id != null) ? (id) : "Unassigned") + ", name=" + name + ", surname=" + surname + "]";
	}

}
