package com.samuraiDigital.adminsystem.api.profile;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.samuraiDigital.adminsystem.data.model.UserSecurityDetails;
import com.samuraiDigital.adminsystem.data.repositories.UserSecurityDetailsRepository;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@RestController
@OpenAPIDefinition(info = @Info(contact = @Contact(email = "dominik.ricko@nth.ch", name = "Dominik Ričko"), title = "Profile API", description = "API for fetching user profiles"))
@RequestMapping(path = { "/e/api/v1/profile", "/i/api/v1/profile" })
public class ApiProfileController {

	private UserSecurityDetailsRepository detailsRepository;
	private ApiProfileService apiProfileService;

	public ApiProfileController(UserSecurityDetailsRepository detailsRepository, ApiProfileService apiProfileService) {
		super();
		this.detailsRepository = detailsRepository;
		this.apiProfileService = apiProfileService;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> getProfile() {

		UserSecurityDetails user;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		Optional<UserSecurityDetails> optionalUser;
		optionalUser = detailsRepository.findByUsername(auth.getName());

		if (optionalUser.isEmpty()) {
			return new ResponseEntity<>("Not logged in, and no passing argument", HttpStatus.BAD_REQUEST);
		}

		user = optionalUser.get();

		ApiProfileResource profile = apiProfileService.convertToResource(user);

		return new ResponseEntity<>(profile, HttpStatus.OK);

	}

	@RequestMapping(method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('READ_PROFILES')")
	public ResponseEntity<?> getForeignProfile(@RequestParam Integer id) {

		UserSecurityDetails user;

		if (id == null) {

			return new ResponseEntity<>("Missing argument", HttpStatus.BAD_REQUEST);

		}
		Optional<UserSecurityDetails> optionalUser;
		optionalUser = detailsRepository.findById(id);

		if (optionalUser.isEmpty()) {
			return new ResponseEntity<>("User not found.", HttpStatus.NOT_FOUND);
		}

		user = optionalUser.get();

		ApiProfileResource profile = apiProfileService.convertToResource(user);

		return new ResponseEntity<>(profile, HttpStatus.OK);

	}

	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<?> updateProfile(@RequestParam ApiProfileResource profile) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth.getName() == profile.getUsername() || auth.getAuthorities().stream().anyMatch(it -> it.getAuthority() == "WRITE_PROFILES")) {

			return new ResponseEntity<>(apiProfileService.updateUserProfile(profile), HttpStatus.OK);

		}
		else
			return new ResponseEntity<>("Unauthorized", HttpStatus.FORBIDDEN);
	}

}
