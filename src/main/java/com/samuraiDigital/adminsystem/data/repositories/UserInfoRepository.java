package com.samuraiDigital.adminsystem.data.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.samuraiDigital.adminsystem.data.model.UserInfo;

public interface UserInfoRepository extends CrudRepository<UserInfo, Long>{

	Optional<UserInfo> findByName(String name);
	
}
