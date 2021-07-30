package com.samuraiDigital.adminsystem.web.model;

import java.util.Optional;

import com.samuraiDigital.adminsystem.data.model.UserSecurityDetails;

public class UrlDataModel {

	private UrlDataAction action;
	private Optional<UserSecurityDetails> userDetails;

	public UrlDataAction getAction() {
		return action;
	}

	public void setAction(UrlDataAction action) {
		this.action = action;
	}

	public Optional<UserSecurityDetails> getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(UserSecurityDetails userDetails) {
		this.userDetails = Optional.of(userDetails);
	}

}
