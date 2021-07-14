package com.samuraiDigital.adminsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication()
@ComponentScan({"com.samuraiDigital.adminsystem"})
public class AdminSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdminSystemApplication.class, args);
	}

}
