package com.samuraiDigital.adminsystem.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

import com.samuraiDigital.adminsystem.data.model.User;

public class UserSecurityDetails implements org.springframework.security.core.userdetails.UserDetails{
	
	private User user;

	public User getUser() {
		return user;
	}

	public UserSecurityDetails(User user) {
		super();
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}
	
	

}
