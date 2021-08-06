package com.samuraiDigital.adminsystem.api.profile;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.samuraiDigital.adminsystem.data.model.UserSecurityDetails;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@RestController
@OpenAPIDefinition(info = @Info(contact = @Contact(email = "dominik.ricko@nth.ch", name = "Dominik Ričko"), title = "Profile API", description = "API for fetching user profiles"))
@RequestMapping(path = { "/e/api/v1/profile", "/i/api/v1/profile" })
public class ApiProfileController {


	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> getProfile(@RequestParam Integer id) {

		UserSecurityDetails user;

		if (id == null) {
			//get from security, email -> repo
		} else {
			//get from request parameter
		}

		//or better yet, hide this all in a service?

		return new ResponseEntity<>("", HttpStatus.OK);

	}

}
