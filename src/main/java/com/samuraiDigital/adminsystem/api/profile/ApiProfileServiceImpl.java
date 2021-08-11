package com.samuraiDigital.adminsystem.api.profile;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import com.samuraiDigital.adminsystem.data.model.SecurityGroup;
import com.samuraiDigital.adminsystem.data.model.UserInfo;
import com.samuraiDigital.adminsystem.data.model.UserSecurityDetails;
import com.samuraiDigital.adminsystem.data.repositories.UserSecurityDetailsRepository;

@Service
public class ApiProfileServiceImpl implements ApiProfileService {

	private UserSecurityDetailsRepository detailsRepository;

	public ApiProfileServiceImpl(UserSecurityDetailsRepository detailsRepository) {
		super();
		this.detailsRepository = detailsRepository;
	}

	@Override
	public ApiProfileResource convertToResource(UserSecurityDetails user) {

		ApiProfileResource userProfile = new ApiProfileResource();

		userProfile.setId(user.getId());
		userProfile.setUsername(user.getUsername());
		userProfile.setEmail(user.getEmail());
		userProfile.setAccount_expiration_date(user.getAccountExpirationDate().toString());
		userProfile.setCredentials_expiration_date(user.getCredentialsExpirationDate().toString());
		userProfile.setEnabled(user.isEnabled());
		userProfile.setGroups(user.getGroups().stream().map(it -> it.getName()).collect(Collectors.toSet()));
		userProfile.setPrivileges(user.getAuthorities().stream().map(it -> it.getAuthority()).collect(Collectors.toSet()));

		Optional<UserInfo> userInfoOptional = user.getUser();

		if(userInfoOptional.isPresent()) {

			UserInfo userInfo = userInfoOptional.get();

			userProfile.setName(userInfo.getName());
			userProfile.setSurname(userInfo.getSurname());
			userProfile.setAddress(userInfo.getAddress());
			userProfile.setBirthdate(userInfo.getBirthdate().toString());

		}

		return userProfile;
	}

	@Override
	public ApiProfileResource updateUserProfile(ApiProfileResource user) {

		DateTimeFormatter dateFormat = DateTimeFormatter.ISO_DATE_TIME;
		Optional<UserSecurityDetails> userDetailsOptional = detailsRepository.findById(user.getId());

		if (userDetailsOptional.isEmpty()) {
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "User not found.");
		}

		UserSecurityDetails userDetails = userDetailsOptional.get();
		Optional<UserInfo> userInfoOptional = userDetails.getUser();

		if (userInfoOptional.isPresent()) {
			UserInfo userInfo = userInfoOptional.get();

			userInfo.setAddress(user.getAddress());
			userInfo.setBirthdate(LocalDate.parse(user.getBirthdate(), dateFormat));
			userInfo.setName(user.getName());
			userInfo.setSurname(user.getSurname());
		}

		userDetails.setAccountExpirationDate(LocalDate.parse(user.getAccount_expiration_date(), dateFormat));
		userDetails.setCredentialsExpirationDate(LocalDate.parse(user.getCredentials_expiration_date(), dateFormat));
		userDetails.setEmail(user.getEmail());
		userDetails.setEnabled(user.getEnabled());
		userDetails.setGroups(user.getGroups().stream().map(it -> new SecurityGroup(it)).collect(Collectors.toSet()));
		userDetails.setUsername(user.getUsername());

		detailsRepository.save(userDetails);

		return user;
	}

}
