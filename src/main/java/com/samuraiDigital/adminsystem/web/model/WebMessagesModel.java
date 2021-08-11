package com.samuraiDigital.adminsystem.web.model;

import java.util.ArrayList;

public class WebMessagesModel {

	private ArrayList<String> messages = new ArrayList<>();

	public ArrayList<String> getMessages() {
		return messages;
	}

	public void setMessages(ArrayList<String> messages) {
		this.messages = messages;
	}

	public void addMessages(String... messages) {
		for (String message : messages) {
			this.messages.add(message);
		}
	}

}
