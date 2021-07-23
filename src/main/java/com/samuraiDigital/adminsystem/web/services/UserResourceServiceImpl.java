package com.samuraiDigital.adminsystem.web.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.samuraiDigital.adminsystem.data.model.UserInfo;
import com.samuraiDigital.adminsystem.data.model.UserSecurityDetails;
import com.samuraiDigital.adminsystem.data.repositories.UserSecurityDetailsRepository;
import com.samuraiDigital.adminsystem.web.resources.UserResource;

@Service
public class UserResourceServiceImpl implements UserResourceService {

	private UserSecurityDetailsRepository userDetailsRepository;

	public UserResourceServiceImpl(UserSecurityDetailsRepository userDetailsRepository) {
		super();
		this.userDetailsRepository = userDetailsRepository;
	}
	
	private Collection<UserResource> convertToUserResources(Iterable<UserSecurityDetails> details) {
		
		ArrayList<UserResource> resources = new ArrayList<>();
		
		for( UserSecurityDetails user : details) {
			
			Optional<UserInfo> userInfoOptional = user.getUser();
			
			if(userInfoOptional.isPresent()) {
				UserInfo userInfo = userInfoOptional.get();
				
				resources.add(new UserResource(
						user.getId(),
						userInfo.getName(),
						userInfo.getSurname(),
						userInfo.getBirthdate(),
						user.getGroups().stream().map(it -> it.getName()).toList(),
						user.getEmail(),
						user.isEnabled(),
						user.getAccountExpirationDate(),
						user.getCredentialsExpirationDate()
					));	
			}else {
				resources.add(new UserResource(
						user.getId(),
						null,
						null,
						null,
						user.getGroups().stream().map(it -> it.getName()).toList(),
						user.getEmail(),
						user.isEnabled(),
						user.getAccountExpirationDate(),
						user.getCredentialsExpirationDate()
						));
			}
			
		}
		
		return resources;
	}
	
	@Override
	public Collection<UserResource> getUsers() {

		Iterable<UserSecurityDetails> usersDetails = userDetailsRepository.findAll();

		return this.convertToUserResources(usersDetails);
		
	}

}
