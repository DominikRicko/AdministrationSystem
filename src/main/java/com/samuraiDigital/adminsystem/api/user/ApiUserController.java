package com.samuraiDigital.adminsystem.api.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@OpenAPIDefinition(info = @Info(title = "User API", description = "API for administration of users", contact = @Contact(name = "Dominik Ričko", email = "dominik.ricko@gmail.com")))
@RequestMapping(path = { "/e/api/v1/users", "/i/api/v1/users" })
public class ApiUserController {

	private ApiUserService resourceService;

	public ApiUserController(ApiUserService resourceService) {
		super();
		this.resourceService = resourceService;
	}

	@RequestMapping(method = RequestMethod.GET)
	@Operation(summary = "Get all users.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Fetched all users.", content = @Content(mediaType = "application/json")) })
	@PreAuthorize("hasAuthority('READ_PROFILES')")
	public ResponseEntity<?> getUsers() {
		return new ResponseEntity<>(resourceService.getUsers(), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, path = "/{id}")
	@Operation(summary = "Gets the single user from id value.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Found the user.", content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "404", description = "User not found.", content = @Content(mediaType = "none")),
			@ApiResponse(responseCode = "400", description = "Did not receive an id", content = @Content(mediaType = "none")),
	})
	@PreAuthorize("hasAuthority('WRITE_PROFILES')")
	public ResponseEntity<?> getUser(@PathVariable @Parameter String id) {

		ApiUserResource user = resourceService.getUser(id);

		return new ResponseEntity<>(user, HttpStatus.OK);

	}

	@RequestMapping(method = RequestMethod.POST)
	@Operation(summary = "Creates a new user from input data.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "User created.", content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "418", description = "Error creating a user, either username or email already exists in database.", content = @Content(mediaType = "none")),
			@ApiResponse(responseCode = "422", description = "Failed creating a user.") })
	@PreAuthorize("hasAuthority('WRITE_PROFILES')")
	public ResponseEntity<?> createUser(ApiUserResource user) {

		ApiUserResource newUserResource = resourceService.saveUser(user);

		return new ResponseEntity<>(newUserResource, HttpStatus.CREATED);

	}

	@RequestMapping(method = RequestMethod.DELETE, path = "{userId}")
	@Operation(summary = "Deletes an existing user with supplied id.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "User successfully deleted.", content = @Content(mediaType = "none")),
			@ApiResponse(responseCode = "404", description = "User not found.", content = @Content(mediaType = "none")) })
	@PreAuthorize("hasAuthority('WRITE_PROFILES')")
	public ResponseEntity<?> removeUser(@PathVariable @Parameter String userId) {

		resourceService.deleteUserById(userId);
		return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);

	}

	@RequestMapping(method = RequestMethod.PUT, path = "{userId}")
	@Operation(summary = "Updates an existing user.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "User succuessfully updated.", content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "418", description = "User could not be updated, check username and email.", content = @Content(mediaType = "none")),
			@ApiResponse(responseCode = "422", description = "Failed creating a user.") })
	@PreAuthorize("hasAuthority('WRITE_PROFILES')")
	public ResponseEntity<?> updateUser(@PathVariable @Parameter String userId, ApiUserResource user) {

		ApiUserResource newUserResource = resourceService.updateUser(userId, user);

		return new ResponseEntity<>(newUserResource, HttpStatus.CREATED);

	}

}
