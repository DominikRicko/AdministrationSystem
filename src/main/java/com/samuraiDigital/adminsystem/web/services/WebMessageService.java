package com.samuraiDigital.adminsystem.web.services;

import org.springframework.ui.Model;

public interface WebMessageService {

	void addMessageToModel(Model model, String... messages);

	void removeMessagesFromModel(Model model);

}
