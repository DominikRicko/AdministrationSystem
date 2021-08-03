package com.samuraiDigital.adminsystem.web.services;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.samuraiDigital.adminsystem.web.model.WebMessagesModel;

@Service(value = "errorService")
public class WebErrorService implements WebMessageService {

	private static final String KEY_ERROR = "errors";

	@Override
	public void addMessageToModel(Model model, String... messages) {

		WebMessagesModel messagesModel = (WebMessagesModel) model.getAttribute(KEY_ERROR);
		if (messagesModel == null) {
			messagesModel = new WebMessagesModel();
		}

		messagesModel.addMessages(messages);
		model.addAttribute(KEY_ERROR, messagesModel);
	}

	@Override
	public void removeMessagesFromModel(Model model) {
		model.addAttribute(KEY_ERROR, null);
	}

}
