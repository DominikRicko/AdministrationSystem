package com.samuraiDigital.adminsystem.web.services;

import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public interface WebMessageService {

	void addMessageToModel(Model model, String... messages);

	void addMessageToModel(RedirectAttributes model, String... messages);

	void removeMessagesFromModel(Model model);

}
