package com.samuraiDigital.adminsystem.data.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.samuraiDigital.adminsystem.data.model.SecurityGroup;

public interface SecurityGroupRepository extends CrudRepository<SecurityGroup, Integer> {

	Optional<SecurityGroup> findByName(String name);

}
