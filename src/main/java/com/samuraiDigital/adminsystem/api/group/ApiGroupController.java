package com.samuraiDigital.adminsystem.api.group;

import javax.websocket.server.PathParam;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@RestController
@OpenAPIDefinition(info = @Info(contact = @Contact(email = "dominik.ricko@nth.ch", name = "Dominik Ričko"), description = "API for editing groups", title = "Groups API"))
@RequestMapping(path = { "/e/api/v1/groups", "/i/api/v1/groups" })
public class ApiGroupController {

	private ApiGroupService groupService;

	public ApiGroupController(ApiGroupService groupService) {
		super();
		this.groupService = groupService;
	}

	@RequestMapping(method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('READ_GROUPS')")
	public ResponseEntity<?> getGroups() {

		return new ResponseEntity<>(groupService.getGroups(), HttpStatus.OK);

	}

	@RequestMapping(method = RequestMethod.GET, path = "/{id}")
	@PreAuthorize("hasAuthority('READ_GROUPS')")
	public ResponseEntity<?> getGroupMembers(@PathParam("id") Integer groupId) {

		return new ResponseEntity<>(groupService.getGroup(groupId), HttpStatus.OK);

	}

	@RequestMapping(method = RequestMethod.PUT, path = "/{id}")
	@PreAuthorize("hasAuthority('WRITE_GROUPS')")
	public ResponseEntity<?> updateGroup(@PathParam("id") Integer groupId, @RequestParam ApiGroupResource group) {

		return new ResponseEntity<>(groupService.updateGroup(groupId, group), HttpStatus.OK);

	}

	@RequestMapping(method = RequestMethod.POST)
	@PreAuthorize("hasAuthority('WRITE_GROUPS')")
	public ResponseEntity<?> createGroup(@RequestParam ApiGroupResource group) {

		return new ResponseEntity<>(groupService.createGroup(group), HttpStatus.OK);

	}

	@RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
	@PreAuthorize("hasAuthority('WRITE_GROUPS')")
	public ResponseEntity<?> deleteGroup(@PathParam("id") Integer groupId) {

		groupService.deleteGroup(groupId);

		return new ResponseEntity<>("Group successfully deleted.", HttpStatus.OK);

	}
}
