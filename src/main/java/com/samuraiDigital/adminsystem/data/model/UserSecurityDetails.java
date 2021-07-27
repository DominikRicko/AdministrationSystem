package com.samuraiDigital.adminsystem.data.model;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.springframework.security.core.GrantedAuthority;

@Entity
public class UserSecurityDetails implements org.springframework.security.core.userdetails.UserDetails{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String username;
	private String email;
	private String passwordHash;
	private Boolean enabled;
	private LocalDate accountExpirationDate;
	private LocalDate credentialsExpirationDate;

	@OneToOne(mappedBy = "userSecurity")
	@PrimaryKeyJoinColumn
	private UserInfo user;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "group_members", 
		joinColumns = @JoinColumn(name = "id_user"), 
		inverseJoinColumns = @JoinColumn(name = "id_group"))
	@Cascade(CascadeType.ALL)
	private Set<SecurityGroup> groups = new HashSet<>();

	public UserSecurityDetails() {
		super();
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		return groups.stream().map(SecurityGroup::getAuthorities).
				flatMap(Set<Authority>::stream).distinct().collect(Collectors.toSet());
		
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
		return this.accountExpirationDate.isAfter(LocalDate.now());
	}

	@Override
	public boolean isAccountNonLocked() {
		return this.enabled;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return this.credentialsExpirationDate.isAfter(LocalDate.now());
	}

	@Override
	public boolean isEnabled() {
		return this.enabled;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public LocalDate getAccountExpirationDate() {
		return accountExpirationDate;
	}

	public void setAccountExpirationDate(LocalDate accountExpirationDate) {
		this.accountExpirationDate = accountExpirationDate;
	}

	public LocalDate getCredentialsExpirationDate() {
		return credentialsExpirationDate;
	}

	public void setCredentialsExpirationDate(LocalDate credentialsExpirationDate) {
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
		StringBuffer buffer = new StringBuffer("User Security Details: ");
		
		if(this.id != null) {
			buffer.append("Id: ").append(this.id).append(" ");
		}
		buffer.append("Name: ").append(this.username).append(" ");
		buffer.append("Email").append(this.email).append(" ");
		
		return buffer.toString();
	}

}
