package com.samuraiDigital.adminsystem.web.controllers;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.samuraiDigital.adminsystem.web.model.UrlDataModel;
import com.samuraiDigital.adminsystem.web.services.RandomUrlService;

@Controller
public class RandomUrlController {

	private RandomUrlService randomUrlService;

	public RandomUrlController(RandomUrlService randomUrlService) {
		super();
		this.randomUrlService = randomUrlService;
	}

	@RequestMapping(value = "/key/*", method = RequestMethod.GET)
	public String resolveUrl(HttpServletRequest request, Model modelUI) {

		String url = request.getRequestURI();

		Optional<UrlDataModel> modelOptional = randomUrlService.getModelFromUrl(url);

		if (modelOptional.isEmpty()) {
			return "error";
		}

		UrlDataModel model = modelOptional.get();
		modelUI.addAttribute("user", model.getUserDetails().get());

		switch(model.getAction()) {

			case REGISTRATION_CONFIRMATION:
				return "pages/registration_confirm";
			case RESET_PASSWORD:
				return "pages/reset_password_page";
			default:
				return "error";

		}

	}

}
