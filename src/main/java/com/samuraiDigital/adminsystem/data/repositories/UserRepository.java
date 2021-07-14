package com.samuraiDigital.adminsystem.data.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.samuraiDigital.adminsystem.data.model.User;

public interface UserRepository extends CrudRepository<User, Long>{

	Optional<User> findByName(String name);
	
}
