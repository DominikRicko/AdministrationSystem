package com.samuraiDigital.adminsystem.data.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class SecurityGroup {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String name;

	@ManyToMany(fetch = FetchType.EAGER, mappedBy = "groups")
	private Set<Authority> authorities = new HashSet<>();

	@ManyToMany(fetch = FetchType.EAGER, mappedBy = "groups")
	private Set<UserSecurityDetails> members = new HashSet<>();

	public SecurityGroup(String name) {
		super();
		this.name = name;
	}

	public SecurityGroup() {
		super();
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

	public Set<Authority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Set<Authority> authorities) {
		this.authorities = authorities;
	}

	public Set<UserSecurityDetails> getMembers() {
		return members;
	}

	public void setMembers(Set<UserSecurityDetails> members) {
		this.members = members;
	}

	public void addAuthority(Authority authority) {
		this.authorities.add(authority);
	}

	public void removeAuthority(Authority authority) {
		this.authorities.remove(authority);
	}

	public void addMember(UserSecurityDetails member) {
		this.members.add(member);
	}

	public void removeMember(UserSecurityDetails member) {
		this.members.add(member);
	}

	@Override
	public String toString() {
		return "SecurityGroup [id=" + ((id != null) ? (id) : "Unassigned") + ", name=" + name + ", authorities="
				+ authorities + "]";
	}

}
