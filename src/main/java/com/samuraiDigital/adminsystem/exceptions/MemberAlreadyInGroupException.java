package com.samuraiDigital.adminsystem.exceptions;

import com.samuraiDigital.adminsystem.data.model.SecurityGroup;
import com.samuraiDigital.adminsystem.data.model.UserSecurityDetails;

public class MemberAlreadyInGroupException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message;

	public MemberAlreadyInGroupException(UserSecurityDetails member, SecurityGroup securityGroup) {
		super();
		StringBuilder messageBuilder = new StringBuilder();
		messageBuilder.append(member.getUsername()).append(" already exists in group ").append(securityGroup.getName());
		this.message = messageBuilder.toString();
	}

	@Override
	public String getMessage() {
		return message;
	}

	
}
