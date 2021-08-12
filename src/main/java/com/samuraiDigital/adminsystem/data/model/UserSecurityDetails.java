package com.samuraiDigital.adminsystem.data.model;

import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;

@Document
public class UserSecurityDetails implements org.springframework.security.core.userdetails.UserDetails {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private String id;
	private String username;
	private String email;
	private String passwordHash;
	private Boolean enabled;
	private ZonedDateTime accountExpirationDate;
	private ZonedDateTime credentialsExpirationDate;

	@DBRef(lazy = false)
	private UserInfo user;

	@DBRef(lazy = false)
	private Set<SecurityGroup> groups = new HashSet<>();

	public UserSecurityDetails() {
		super();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		return groups.stream().map(SecurityGroup::getAuthorities).flatMap(Set<Authority>::stream).distinct()
				.collect(Collectors.toSet());

	}

	@Override
	public String getPassword() {
		return passwordHash;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return this.accountExpirationDate.isAfter(ZonedDateTime.now());
	}

	@Override
	public boolean isAccountNonLocked() {
		return this.enabled;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return this.credentialsExpirationDate.isAfter(ZonedDateTime.now());
	}

	@Override
	public boolean isEnabled() {
		return this.enabled;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public ZonedDateTime getAccountExpirationDate() {
		return accountExpirationDate;
	}

	public void setAccountExpirationDate(ZonedDateTime accountExpirationDate) {
		this.accountExpirationDate = accountExpirationDate;
	}

	public ZonedDateTime getCredentialsExpirationDate() {
		return credentialsExpirationDate;
	}

	public void setCredentialsExpirationDate(ZonedDateTime credentialsExpirationDate) {
		this.credentialsExpirationDate = credentialsExpirationDate;
	}

	public Optional<UserInfo> getUser() {
		return Optional.ofNullable(user);
	}

	public void setUser(UserInfo user) {
		this.user = user;
	}

	public Set<SecurityGroup> getGroups() {
		return groups;
	}

	public void setGroups(Set<SecurityGroup> groups) {
		this.groups = groups;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void addGroup(SecurityGroup group) {
		this.groups.add(group);
	}

	@Override
	public String toString() {
		return "UserSecurityDetails [id=" + ((id != null) ? (id) : "Unassigned") + ", username=" + username + ", email="
				+ email + ", enabled=" + enabled + ", accountExpirationDate="
				+ accountExpirationDate + ", credentialsExpirationDate=" + credentialsExpirationDate + "]";
	}


}
