package com.samuraiDigital.adminsystem.data.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.samuraiDigital.adminsystem.data.model.UserSecurityDetails;

public interface UserSecurityDetailsRepository extends CrudRepository<UserSecurityDetails, Integer> {

	Optional<UserSecurityDetails> findByUsername(String username);

	Optional<UserSecurityDetails> findByEmail(String email);

}
