package com.samuraiDigital.adminsystem.api.user;

import java.text.ParsePosition;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Locale;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.samuraiDigital.adminsystem.data.model.SecurityGroup;
import com.samuraiDigital.adminsystem.data.model.UserInfo;
import com.samuraiDigital.adminsystem.data.model.UserSecurityDetails;
import com.samuraiDigital.adminsystem.data.repositories.SecurityGroupRepository;
import com.samuraiDigital.adminsystem.data.repositories.UserInfoRepository;
import com.samuraiDigital.adminsystem.data.repositories.UserSecurityDetailsRepository;

@Service
public class UserResourceServiceImpl implements UserResourceService{

	private UserSecurityDetailsRepository userDetailsRepository;
	private UserInfoRepository userInfoRepository;
	private SecurityGroupRepository groupRepository;

	public UserResourceServiceImpl(UserSecurityDetailsRepository userDetailsRepository,
			UserInfoRepository userInfoRepository, SecurityGroupRepository groupRepository) {
		super();
		this.userDetailsRepository = userDetailsRepository;
		this.userInfoRepository = userInfoRepository;
		this.groupRepository = groupRepository;
	}

	private UserResource convertToUserResource(UserSecurityDetails user) {
		Optional<UserInfo> userInfoOptional = user.getUser();

		if (userInfoOptional.isPresent()) {
			UserInfo userInfo = userInfoOptional.get();

			return new UserResource(user.getId(), userInfo.getName(), userInfo.getSurname(),
					userInfo.getBirthdate().toString(), user.getGroups().stream().map(it -> it.getName()).toList(),
					user.getEmail(), user.getUsername(), user.isEnabled(), user.getAccountExpirationDate().toString(),
					user.getCredentialsExpirationDate().toString());
		} else {
			return new UserResource(user.getId(), null, null, null,
					user.getGroups().stream().map(it -> it.getName()).toList(), user.getEmail(), user.getUsername(), user.isEnabled(),
					user.getAccountExpirationDate().toString(), user.getCredentialsExpirationDate().toString());
		}
	}
	
	private Collection<UserResource> convertToUserResources(Iterable<UserSecurityDetails> details) {

		ArrayList<UserResource> resources = new ArrayList<>();

		for (UserSecurityDetails user : details) {

			resources.add(convertToUserResource(user));

		}

		return resources;
	}

	//TODO: Separate updating from creation of new users in this method.
	private UserResource saveToRepository(UserResource userResources) {

		Optional<UserSecurityDetails> userFromRepo;
		UserSecurityDetails newUserSecurity;
		UserInfo newUserInfo;
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("EEE MMM dd yyyy HH:mm:ss 'GMT'Z", Locale.US);

		if (userResources.getId() == null) {
			userFromRepo = Optional.ofNullable(null);
		} else {
			userFromRepo = userDetailsRepository.findById(userResources.getId());
		}

		if (userFromRepo.isPresent()) {
			newUserSecurity = userFromRepo.get();
		} else {
			newUserSecurity = new UserSecurityDetails();
			newUserSecurity.setPasswordHash("placeholder");
		}

		newUserSecurity.setEmail(userResources.getEmail());
		newUserSecurity.setUsername(userResources.getUsername());
		newUserSecurity.setEnabled(userResources.getEnabled());
		newUserSecurity.setAccountExpirationDate(LocalDate.from(dateFormat.parse(userResources.getAccount_expiration_date(), new ParsePosition(0))));
		newUserSecurity.setCredentialsExpirationDate(LocalDate.from(dateFormat.parse(userResources.getCredentials_expiration_date(), new ParsePosition(0))));

		String[] groups = userResources.getGroups().split("\n");
		
		for(String group : groups) {
			
			Optional<SecurityGroup> foundSecurityGroup = groupRepository.findByName(group);
			SecurityGroup sGroup;
			
			if(foundSecurityGroup.isPresent()) {
				sGroup = foundSecurityGroup.get();
			}else {
				sGroup = new SecurityGroup(group);
				groupRepository.save(sGroup);
			}
			
			newUserSecurity.addGroup(sGroup);
		}
		
		newUserSecurity = userDetailsRepository.save(newUserSecurity);
		Optional<UserInfo> userInfoFromSecurity = newUserSecurity.getUser();

		if (userInfoFromSecurity.isPresent()) {
			newUserInfo = userInfoFromSecurity.get();
		} else {
			newUserInfo = new UserInfo();
			newUserInfo.setId(newUserSecurity.getId());
		}

		newUserInfo.setBirthdate(LocalDate.from(dateFormat.parse(userResources.getBirthdate(), new ParsePosition(0))));
		newUserInfo.setName(userResources.getName());
		newUserInfo.setSurname(userResources.getSurname());

		newUserInfo.setUserSecurity(newUserSecurity);
		userInfoRepository.save(newUserInfo);
		
		return new UserResource(newUserInfo.getId() ,userResources);
	}

	@Override
	public Collection<UserResource> getUsers() {

		Iterable<UserSecurityDetails> usersDetails = userDetailsRepository.findAll();

		return this.convertToUserResources(usersDetails);

	}

	@Override
	public Optional<UserResource> getUser(Integer id) {
		
		Optional<UserSecurityDetails> user = userDetailsRepository.findById(id);
		
		if(user.isPresent())
			return Optional.of(convertToUserResource(user.get()));
		else
			return Optional.ofNullable(null);
		
	}

	@Override
	public Optional<UserResource> saveUser(UserResource user) {

		try {
			return Optional.of(saveToRepository(user));
		}
		catch(Exception e) {
			e.printStackTrace();
			return Optional.ofNullable(null);
		}
	}

	@Override
	public Boolean deleteUserById(Integer id) {
		try {
			userInfoRepository.deleteById(id);
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

	@Override
	public Optional<UserResource> updateUser(Integer id, UserResource user) {
		try {
			return Optional.of(saveToRepository(user));
		}
		catch(Exception e) {
			e.printStackTrace();
			return Optional.ofNullable(null);
		}
	}

}
