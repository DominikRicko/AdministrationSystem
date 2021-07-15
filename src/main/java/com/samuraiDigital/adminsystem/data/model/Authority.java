package com.samuraiDigital.adminsystem.data.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;

import org.springframework.security.core.GrantedAuthority;

import com.samuraiDigital.adminsystem.exceptions.GroupAlreadyHasAuthorityException;
import com.samuraiDigital.adminsystem.exceptions.GroupLacksAuthorityException;

@Entity
public class Authority implements GrantedAuthority {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	@Lob
	private String description;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "group_authority",
		joinColumns = @JoinColumn(name = "id_authority"),
		inverseJoinColumns = @JoinColumn(name = "id_group"))
	private Set<SecurityGroup> groups = new HashSet<SecurityGroup>();

	public Authority() {
		super();
	}

	public Authority(String name, String description) {
		super();
		this.name = name;
		this.description = description;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<SecurityGroup> getGroups() {
		return groups;
	}

	public void setGroups(Set<SecurityGroup> groups) {
		this.groups = groups;
	}
	
	public void addGroup(SecurityGroup group) {
		if(this.groups.contains(group)) throw new GroupAlreadyHasAuthorityException(group, this);
		this.groups.add(group);
	}
	
	public void removeGroup(SecurityGroup group) {
		if(!this.groups.contains(group)) throw new GroupLacksAuthorityException(group, this);
		this.groups.remove(group);
	}
	
	@Override
	public String getAuthority() {
		return name;
	}

	
}
