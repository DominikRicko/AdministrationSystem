package com.samuraiDigital.adminsystem.api.user;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import com.samuraiDigital.adminsystem.data.model.SecurityGroup;
import com.samuraiDigital.adminsystem.data.model.UserInfo;
import com.samuraiDigital.adminsystem.data.model.UserSecurityDetails;
import com.samuraiDigital.adminsystem.data.repositories.SecurityGroupRepository;
import com.samuraiDigital.adminsystem.data.repositories.UserInfoRepository;
import com.samuraiDigital.adminsystem.data.repositories.UserSecurityDetailsRepository;
import com.samuraiDigital.adminsystem.web.services.PasswordResetService;
import com.samuraiDigital.adminsystem.web.services.RegistrationConfirmationService;

@Service
public class ApiUserServiceImpl implements ApiUserService {

	private UserSecurityDetailsRepository userDetailsRepository;
	private UserInfoRepository userInfoRepository;
	private SecurityGroupRepository groupRepository;
	private DateTimeFormatter dateFormat = DateTimeFormatter.ISO_DATE_TIME;
	private RegistrationConfirmationService confirmationService;
	private PasswordResetService passwordResetService;

	public ApiUserServiceImpl(UserSecurityDetailsRepository userDetailsRepository, UserInfoRepository userInfoRepository, SecurityGroupRepository groupRepository, RegistrationConfirmationService confirmationService, PasswordResetService passwordResetService) {
		super();
		this.userDetailsRepository = userDetailsRepository;
		this.userInfoRepository = userInfoRepository;
		this.groupRepository = groupRepository;
		this.confirmationService = confirmationService;
		this.passwordResetService = passwordResetService;
	}

	private Set<SecurityGroup> parseAndSaveGroups(Collection<String> groups) {
		HashSet<SecurityGroup> groupSet = new HashSet<>();

		for (String group : groups) {
			Optional<SecurityGroup> foundSecurityGroup = groupRepository.findByName(group);
			SecurityGroup sGroup;

			if (foundSecurityGroup.isPresent()) {
				sGroup = foundSecurityGroup.get();
			} else {
				sGroup = new SecurityGroup(group);
				groupRepository.save(sGroup);
			}

			groupSet.add(sGroup);

		}

		return groupSet;
	}

	private ApiUserResource convertToUserResource(UserSecurityDetails user) {
		Optional<UserInfo> userInfoOptional = user.getUser();
		ApiUserResource newUserResource = new ApiUserResource();

		newUserResource.setId(user.getId());
		newUserResource.setUsername(user.getUsername());
		newUserResource.setEmail(user.getEmail());
		newUserResource.setAccount_expiration_date(user.getAccountExpirationDate().toString());
		newUserResource.setCredentials_expiration_date(user.getCredentialsExpirationDate().toString());
		newUserResource.setEnabled(user.isEnabled());
		newUserResource.setGroups(user.getGroups().stream().map(it -> it.getName()).collect(Collectors.toList()));

		if (userInfoOptional.isPresent()) {
			UserInfo userInfo = userInfoOptional.get();

			newUserResource.setName(userInfo.getName());
			newUserResource.setSurname(userInfo.getSurname());
			newUserResource.setBirthdate(userInfo.getBirthdate().toString());
		}

		return newUserResource;

	}

	@Override
	public Collection<ApiUserResource> getUsers() {

		Iterable<UserSecurityDetails> usersDetails = userDetailsRepository.findAll();
		ArrayList<ApiUserResource> resources = new ArrayList<>();

		for (UserSecurityDetails user : usersDetails) {

			resources.add(convertToUserResource(user));

		}

		return resources;

	}

	@Override
	public ApiUserResource getUser(Integer id) {

		if (id == null)
			throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Did not send an id.");

		Optional<UserSecurityDetails> user = userDetailsRepository.findById(id);

		if (user.isEmpty())
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND,
					String.format("User with id [%s] is not found.", id));

		return convertToUserResource(user.get());

	}

	@Override
	public ApiUserResource saveUser(ApiUserResource user) {
		DateTimeFormatter dateFormat = DateTimeFormatter.ISO_DATE_TIME;
		UserSecurityDetails newUserSecurity = new UserSecurityDetails();
		UserInfo newUserInfo = new UserInfo();

		if (user.getId() != null) {
			if (userDetailsRepository.findById(user.getId()).isPresent()) {
				throw new HttpClientErrorException(HttpStatus.UNPROCESSABLE_ENTITY,
						String.format("Cannot create a user with an existing id, (id = [%s] ", user.getId()));
			}
		}

		Set<SecurityGroup> groups = parseAndSaveGroups(user.getGroups());

		newUserSecurity.setPasswordHash("placeholder");
		newUserSecurity.setEmail(user.getEmail());
		newUserSecurity.setUsername(user.getUsername());
		newUserSecurity.setEnabled(user.getEnabled());
		newUserSecurity.setAccountExpirationDate(LocalDate.parse(user.getAccount_expiration_date(), dateFormat));
		newUserSecurity
		.setCredentialsExpirationDate(LocalDate.from(dateFormat.parse(user.getCredentials_expiration_date())));
		newUserSecurity.setGroups(groups);

		try {

			newUserSecurity = userDetailsRepository.save(newUserSecurity);
			newUserInfo.setId(newUserSecurity.getId());
			newUserInfo.setUserSecurity(newUserSecurity);
			newUserSecurity.setUser(newUserInfo);

			newUserInfo.setBirthdate(LocalDate.from(dateFormat.parse(user.getBirthdate())));
			newUserInfo.setName(user.getName());
			newUserInfo.setSurname(user.getSurname());

			userInfoRepository.save(newUserInfo);
			userDetailsRepository.save(newUserSecurity);

		} catch (Exception e) {
			e.printStackTrace();
			throw new HttpClientErrorException(HttpStatus.UNPROCESSABLE_ENTITY,
					"Could not process request, either database server is unavailable or conflicts with username or email.");
		}

		if (!user.getEnabled()) {
			confirmationService.requestConfirmation(newUserSecurity);
		}

		passwordResetService.requestPasswordReset(newUserSecurity.getEmail());

		user.setId(newUserSecurity.getId());
		return user;
	}

	@Override
	public void deleteUserById(Integer id) {

		userInfoRepository.deleteById(id);

	}

	@Override
	public ApiUserResource updateUser(Integer id, ApiUserResource user) {
		Optional<UserSecurityDetails> userFromRepo = userDetailsRepository.findById(user.getId());

		if (userFromRepo.isEmpty()) {
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND,
					String.format("User with ID [%s] is not found.", user.getId()));
		}

		UserSecurityDetails userSecurity = userFromRepo.get();
		userSecurity.setAccountExpirationDate(LocalDate.from(dateFormat.parse(user.getAccount_expiration_date())));
		userSecurity
		.setCredentialsExpirationDate(LocalDate.from(dateFormat.parse(user.getCredentials_expiration_date())));
		userSecurity.setEmail(user.getEmail());
		userSecurity.setUsername(user.getUsername());
		userSecurity.setEnabled(user.getEnabled());
		userSecurity.setGroups(parseAndSaveGroups(user.getGroups()));

		Optional<UserInfo> userInfoOptional = userSecurity.getUser();
		UserInfo userInfo;

		if (userInfoOptional.isPresent()) {
			userInfo = userInfoOptional.get();
		} else {
			userInfo = new UserInfo();
			userSecurity.setUser(userInfo);
			userInfo.setId(user.getId());
			userInfo.setUserSecurity(userSecurity);
		}

		userInfo.setName(user.getName());
		userInfo.setSurname(user.getSurname());
		userInfo.setBirthdate(LocalDate.from(dateFormat.parse(user.getBirthdate())));

		try {
			userInfoRepository.save(userInfo);
			userDetailsRepository.save(userSecurity);
		} catch (Exception e) {
			e.printStackTrace();
			throw new HttpClientErrorException(HttpStatus.UNPROCESSABLE_ENTITY,
					"Could not process request, either database server is unavailable or conflicts with username or email.");
		}

		return user;
	}

}
