package com.samuraiDigital.adminsystem.api.user;

import java.util.Collection;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "User", description = "User resource")
public class ApiUserResource {

	@Schema(name = "Id", description = "Unique identifier for user", required = true)
	private Integer id;
	
	@Schema(name = "Name", required = true)
	private String name;
	
	@Schema(name = "Surname", required = true)
	private String surname;
	
	@Schema(name = "Birthdate", required = true)
	private String birthdate;
	
	@ArraySchema(minItems = 0, arraySchema = @Schema(name = "groups", description = "User belongs to these groups."), schema =  @Schema(name = "group"), uniqueItems = true)
	private Collection<String> groups;
	
	@Schema(name = "Email", required = true)
	private String email;
	
	@Schema(name = "Username", required = true)
	private String username;
	
	@Schema(name = "Enabled", description = "Is user's account enabled.", required = true)
	private Boolean enabled;
	
	@Schema(name = "Account expiration date", description = "When does the user's account expire.", required = false)
	private String account_expiration_date;
	
	@Schema(name = "Credentials expiration date", description = "When does the user's credentials expire.",  required = false)
	private String credentials_expiration_date;
	
	public ApiUserResource() {
		super();
	}

	public ApiUserResource(Integer id, String name, String surname, String birthdate, Collection<String> groups,
			String email, String username, Boolean enabled, String account_expiration_date,
			String credentials_expiration_date) {
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

	public String getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	public Collection<String> getGroups() {
		return groups;
	}

	public void setGroups(Collection<String> groups) {
		this.groups = groups;
	}
	
	public void addGroup(String group) {
		this.groups.add(group);
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

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
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

	public void setCredentials_expiration_date(String credentials_expiration_date) {
		this.credentials_expiration_date = credentials_expiration_date;
	}

	@Override
	public String toString() {
		return "UserResource [id=" + ((id != null) ? (id) : "Unassigned") + ", name=" + name + ", surname=" + surname
				+ ", birthdate=" + birthdate + ", groups=" + groups + ", email=" + email + ", username=" + username
				+ ", enabled=" + enabled + ", account_expiration_date=" + account_expiration_date
				+ ", credentials_expiration_date=" + credentials_expiration_date + "]";
	}

}
