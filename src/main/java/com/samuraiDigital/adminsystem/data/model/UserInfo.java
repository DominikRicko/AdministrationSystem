package com.samuraiDigital.adminsystem.data.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
public class UserInfo {

	@Id
	private Integer id;
	private String name;
	private String surname;
	private LocalDate birthdate;
	private String address;

	@OneToOne(fetch = FetchType.EAGER)
	@PrimaryKeyJoinColumn
	@Cascade(CascadeType.ALL)
	private UserSecurityDetails userSecurity = new UserSecurityDetails();

	public UserInfo() {
		super();
	}

	public UserInfo(String name, String surname, LocalDate birthdate, String address) {
		super();
		this.name = name;
		this.surname = surname;
		this.birthdate = birthdate;
		this.address = address;
	}

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

	public LocalDate getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(LocalDate birthdate) {
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
		return "UserInfo [id=" + ((id != null) ? (id) : "Unassigned") + ", name=" + name + ", surname=" + surname
				+ ", birthdate=" + birthdate + ", address=" + address + ", userSecurity=" + userSecurity + "]";
	}

}
