package com.samuraiDigital.adminsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.thymeleaf.extras.springsecurity5.dialect.SpringSecurityDialect;
import org.thymeleaf.spring5.SpringTemplateEngine;

@SpringBootApplication()
@EnableMongoRepositories("com.samuraiDigital.adminsystem.data.repositories")
@EntityScan("com.samuraiDigital.adminsystem.data.model")
@ComponentScan({ "com.samuraiDigital.adminsystem" })
public class AdminSystemApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(AdminSystemApplication.class, args);
		SpringTemplateEngine springTemplateEngine = ctx.getBean(SpringTemplateEngine.class);
		springTemplateEngine.addDialect(new SpringSecurityDialect());
	}

}
