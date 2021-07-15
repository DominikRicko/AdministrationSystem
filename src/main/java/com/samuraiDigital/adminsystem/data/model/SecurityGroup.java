package com.samuraiDigital.adminsystem.data.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.samuraiDigital.adminsystem.exceptions.GroupAlreadyHasAuthority;
import com.samuraiDigital.adminsystem.exceptions.GroupLacksAuthority;
@Entity
public class SecurityGroup {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	@ManyToMany(fetch = FetchType.EAGER, mappedBy = "group_authority")
	private Set<Authority> authorities = new HashSet<>();

	public SecurityGroup(String name) {
		super();
		this.name = name;
	}

	public SecurityGroup() {
		super();
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

	public Set<Authority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Set<Authority> authorities) {
		this.authorities = authorities;
	}
	
	public void addAuthority(Authority authority) {
		if(this.authorities.contains(authority)) throw new GroupAlreadyHasAuthority(this, authority);
		this.authorities.add(authority);
	}
	
	public void removeAuthority(Authority authority) {
		if(!this.authorities.contains(authority)) throw new GroupLacksAuthority(this, authority);
		this.authorities.remove(authority);
	}
	
}
