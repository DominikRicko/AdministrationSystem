package com.samuraiDigital.adminsystem.security.services;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

import com.samuraiDigital.adminsystem.data.model.UserSecurityDetails;
import com.samuraiDigital.adminsystem.data.repositories.UserSecurityDetailsRepository;

@Service
public class JpaUserDetailsManager implements UserDetailsManager{

	private UserSecurityDetailsRepository userRepository;
	
	public JpaUserDetailsManager(UserSecurityDetailsRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<UserSecurityDetails> user = userRepository.findByUsername(username);
		
		if(user.isEmpty()) {
			throw new UsernameNotFoundException(username);
		}
		
		return user.get();
		
	}

	@Override
	public void createUser(UserDetails user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateUser(UserDetails user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteUser(String username) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void changePassword(String oldPassword, String newPassword) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean userExists(String username) {
		// TODO Auto-generated method stub
		return false;
	}

}
