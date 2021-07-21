package com.samuraiDigital.adminsystem.data.model;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
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

import org.springframework.security.core.GrantedAuthority;

@Entity
public class UserSecurityDetails implements org.springframework.security.core.userdetails.UserDetails{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String username;
	private String email;
	private String passwordHash;
	private Boolean enabled;
	private Date accountExpirationDate;
	private Date credentialsExpirationDate;

	@OneToOne(mappedBy = "userSecurity")
	@PrimaryKeyJoinColumn
	private UserInfo user;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "group_members", 
		joinColumns = @JoinColumn(name = "id_user"), 
		inverseJoinColumns = @JoinColumn(name = "id_group"))
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
		return this.accountExpirationDate.after(Date.valueOf(LocalDate.now()));
	}

	@Override
	public boolean isAccountNonLocked() {
		return this.enabled;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return this.credentialsExpirationDate.after(Date.valueOf(LocalDate.now()));
	}

	@Override
	public boolean isEnabled() {
		return this.enabled;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public Date getAccountExpirationDate() {
		return accountExpirationDate;
	}

	public void setAccountExpirationDate(Date accountExpirationDate) {
		this.accountExpirationDate = accountExpirationDate;
	}

	public Date getCredentialsExpirationDate() {
		return credentialsExpirationDate;
	}

	public void setCredentialsExpirationDate(Date credentialsExpirationDate) {
		this.credentialsExpirationDate = credentialsExpirationDate;
	}

	public UserInfo getUser() {
		return user;
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

}
