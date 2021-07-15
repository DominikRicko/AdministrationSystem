package com.samuraiDigital.adminsystem.data.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.samuraiDigital.adminsystem.data.model.UserSecurityDetails;

public interface UserSecurityDetailsRepository extends CrudRepository<UserSecurityDetails, Long>{
	
	Optional<UserSecurityDetails> findByUsername(String username);
	
}
