package com.samuraiDigital.adminsystem.web.services;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.samuraiDigital.adminsystem.web.model.WebMessagesModel;

@Service(value = "infoService")
public class WebInfoService implements WebMessageService {

	private static final String KEY_INFO = "infos";

	@Override
	public void addMessageToModel(Model model, String... messages) {

		WebMessagesModel messagesModel = (WebMessagesModel) model.getAttribute(KEY_INFO);
		if (messagesModel == null) {
			messagesModel = new WebMessagesModel();
		}

		messagesModel.addMessages(messages);
		model.addAttribute(KEY_INFO, messagesModel);
	}

	@Override
	public void removeMessagesFromModel(Model model) {
		model.addAttribute(KEY_INFO, null);
	}

}
