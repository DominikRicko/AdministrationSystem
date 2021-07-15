package com.samuraiDigital.adminsystem.exceptions;

import com.samuraiDigital.adminsystem.data.model.Authority;
import com.samuraiDigital.adminsystem.data.model.SecurityGroup;

public class GroupAlreadyHasAuthorityException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message;
	
	public GroupAlreadyHasAuthorityException(SecurityGroup group, Authority authority) {
		
		super();
		StringBuilder messageBuilder = new StringBuilder("Group: ");
		messageBuilder.append(group.getName()).append(" | Authority: ").append(authority.getName());
		this.message = messageBuilder.toString();
		
	}

	@Override
	public String getMessage() {
		return message;
	}
	
}
