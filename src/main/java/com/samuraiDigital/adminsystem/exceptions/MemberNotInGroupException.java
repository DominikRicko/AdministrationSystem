package com.samuraiDigital.adminsystem.exceptions;

import com.samuraiDigital.adminsystem.data.model.SecurityGroup;
import com.samuraiDigital.adminsystem.data.model.UserSecurityDetails;

public class MemberNotInGroupException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MemberNotInGroupException(UserSecurityDetails member, SecurityGroup securityGroup) {
		
		StringBuilder messageBuilder = new StringBuilder();
		messageBuilder.append(member.getUsername()).append(" is already in group ").append(securityGroup.getName());
		this.message = messageBuilder.toString();
		
	}

	private String message;

	@Override
	public String getMessage() {
		return this.message;
	}

}
