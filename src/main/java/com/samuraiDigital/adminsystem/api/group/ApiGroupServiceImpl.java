package com.samuraiDigital.adminsystem.api.group;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import com.samuraiDigital.adminsystem.data.model.SecurityGroup;
import com.samuraiDigital.adminsystem.data.repositories.SecurityGroupRepository;
import com.samuraiDigital.adminsystem.data.repositories.UserSecurityDetailsRepository;

@Service
public class ApiGroupServiceImpl implements ApiGroupService {

	private SecurityGroupRepository groupRepository;
	private UserSecurityDetailsRepository detailsRepository;

	public ApiGroupServiceImpl(SecurityGroupRepository groupRepository) {
		super();
		this.groupRepository = groupRepository;
	}

	private ApiGroupResource convertToGroupResource(SecurityGroup secGroup) {

		ApiGroupResource group = new ApiGroupResource();

		group.setId(secGroup.getId());
		group.setName(secGroup.getName());
		group.setMembers(secGroup.getMembers().stream().map(it -> it.getUsername()).collect(Collectors.toSet()));

		return group;

	}

	private SecurityGroup convertAndUpdateToDB(SecurityGroup group, ApiGroupResource resource) {

		group.setName(resource.getName());
		group.setMembers(resource.getMembers().stream().map(it -> detailsRepository.findByUsername(it).get()).collect(Collectors.toSet()));

		groupRepository.save(group);

		return group;

	}

	private SecurityGroup convertAndSaveToDb(ApiGroupResource resource) {

		return convertAndUpdateToDB(new SecurityGroup(), resource);

	}

	@Override
	public Collection<ApiGroupResource> getGroups() {

		Iterable<SecurityGroup> groups = groupRepository.findAll();
		ArrayList<ApiGroupResource> resources = new ArrayList<>();

		for (SecurityGroup group : groups) {

			resources.add(convertToGroupResource(group));

		}

		return resources;
	}

	@Override
	public ApiGroupResource getGroup(Integer id) {

		Optional<SecurityGroup> groupOptional = groupRepository.findById(id);

		if (groupOptional.isEmpty()) {
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "Requested group is not found.");
		}

		return convertToGroupResource(groupOptional.get());

	}

	@Override
	public ApiGroupResource createGroup(ApiGroupResource group) {

		return convertToGroupResource(convertAndSaveToDb(group));
	}

	@Override
	public ApiGroupResource updateGroup(Integer id, ApiGroupResource newGroup) {

		Optional<SecurityGroup> groupOptional = groupRepository.findById(id);

		if (groupOptional.isEmpty()) {
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "Requested group is not found.");
		}

		SecurityGroup group = groupOptional.get();

		return convertToGroupResource(convertAndUpdateToDB(group, newGroup));
	}

	@Override
	public void deleteGroup(Integer id) {

		groupRepository.deleteById(id);

	}

}
