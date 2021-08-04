package com.samuraiDigital.adminsystem.web.controllers;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.samuraiDigital.adminsystem.web.model.UrlDataAction;
import com.samuraiDigital.adminsystem.web.model.UrlDataModel;
import com.samuraiDigital.adminsystem.web.services.PasswordResetService;
import com.samuraiDigital.adminsystem.web.services.RandomUrlService;
import com.samuraiDigital.adminsystem.web.services.RegistrationConfirmationService;
import com.samuraiDigital.adminsystem.web.services.WebMessageService;

@Controller
public class RandomUrlController {

	private RandomUrlService randomUrlService;
	private RegistrationConfirmationService confirmationService;
	private PasswordResetService passwordResetService;
	private WebMessageService errorService;
	private WebMessageService infoService;

	public RandomUrlController(RandomUrlService randomUrlService, RegistrationConfirmationService confirmationService, PasswordResetService passwordResetService, WebMessageService errorService, WebMessageService infoService) {
		super();
		this.randomUrlService = randomUrlService;
		this.confirmationService = confirmationService;
		this.passwordResetService = passwordResetService;
		this.errorService = errorService;
		this.infoService = infoService;
	}

	@RequestMapping(value = "/key/*", method = RequestMethod.GET)
	public String resolveUrl(HttpServletRequest request, RedirectAttributes modelUI) {

		String url = request.getRequestURI();

		Optional<UrlDataModel> modelOptional = randomUrlService.getModelFromUrl(url);

		if (modelOptional.isEmpty()) {

			errorService.addMessageToModel(modelUI, "Unknown URL.");

			return "redirect:/login";
		}

		UrlDataModel model = modelOptional.get();
		modelUI.addAttribute("user", model.getUserDetails().get());

		switch(model.getAction()) {

			case REGISTRATION_CONFIRMATION:

				confirmationService.confirmRegistration(model.getUserDetails().get());
				randomUrlService.removeDataModel(url);

				infoService.addMessageToModel(modelUI, "Your account has been confirmed, please log in.");

				return "redirect:/login";
			case RESET_PASSWORD:

				String randomUrl = randomUrlService.getRandomUrl();
				UrlDataModel dataModel = new UrlDataModel();

				dataModel.setAction(UrlDataAction.NEW_PASSWORD);
				dataModel.setUserDetails(model.getUserDetails().get());
				dataModel.setExtra(url);

				modelUI.addAttribute("requestUrl", randomUrl);
				randomUrlService.bindModelToUrl(randomUrl, dataModel);

				return "redirect:/reset_password_started";
			default:

				errorService.addMessageToModel(modelUI, "Unknown URL action.");
				return "redirect:/login";

		}

	}

	@RequestMapping(value = "/key/*", method = RequestMethod.POST)
	public String resolvePostUrl(HttpServletRequest request, RedirectAttributes modelUI, @RequestParam("password") String newPassword) {

		String url = request.getRequestURI();

		Optional<UrlDataModel> modelOptional = randomUrlService.getModelFromUrl(url);

		if(modelOptional.isEmpty()) {

			errorService.addMessageToModel(modelUI, "URL not valid.");

			return "redirect:/login";
		}

		UrlDataModel model = modelOptional.get();
		modelUI.addAttribute("user", model.getUserDetails().get());

		switch(model.getAction()) {
			case NEW_PASSWORD:

				passwordResetService.resetPassword(model.getUserDetails().get(), newPassword);

				randomUrlService.removeDataModel(url);
				randomUrlService.removeDataModel(model.getExtra().get());

				infoService.addMessageToModel(modelUI, "Your password has been successfully changed, please log in.");

				return "redirect:/login";

			default:

				errorService.addMessageToModel(modelUI, "Unknown URL action.");

				return "redirect:/login";
		}


	}

}
