package com.samuraiDigital.adminsystem.data.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class UserInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String surname;
	private Date birthdate;
	private String address;
	
	@OneToOne(fetch = FetchType.EAGER)
	private UserSecurityDetails userSecurity = new UserSecurityDetails();

	public UserInfo() {
		super();
	}

	public UserInfo(String name, String surname, Date birthdate, String address) {
		super();
		this.name = name;
		this.surname = surname;
		this.birthdate = birthdate;
		this.address = address;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
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
	
}
