package com.samuraiDigital.adminsystem.web.controllers;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.samuraiDigital.adminsystem.web.model.UrlDataAction;
import com.samuraiDigital.adminsystem.web.model.UrlDataModel;
import com.samuraiDigital.adminsystem.web.services.PasswordResetService;
import com.samuraiDigital.adminsystem.web.services.RandomUrlService;
import com.samuraiDigital.adminsystem.web.services.RegistrationConfirmationService;

@Controller
public class RandomUrlController {

	private RandomUrlService randomUrlService;
	private RegistrationConfirmationService confirmationService;
	private PasswordResetService passwordResetService;

	public RandomUrlController(RandomUrlService randomUrlService, RegistrationConfirmationService confirmationService, PasswordResetService passwordResetService) {
		super();
		this.randomUrlService = randomUrlService;
		this.confirmationService = confirmationService;
		this.passwordResetService = passwordResetService;
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

				confirmationService.confirmRegistration(model.getUserDetails().get());
				randomUrlService.removeDataModel(url);

				return "pages/registration_confirm";
			case RESET_PASSWORD:

				String randomUrl = randomUrlService.getRandomUrl();
				UrlDataModel dataModel = new UrlDataModel();

				dataModel.setAction(UrlDataAction.NEW_PASSWORD);
				dataModel.setUserDetails(model.getUserDetails().get());
				dataModel.setExtra(url);

				modelUI.addAttribute("requestUrl", randomUrl);
				randomUrlService.bindModelToUrl(randomUrl, dataModel);

				return "pages/reset_password_started";
			default:
				return "error";

		}

	}

	@RequestMapping(value = "/key/*", method = RequestMethod.POST)
	public ResponseEntity<?> resolvePostUrl(HttpServletRequest request, Model modelUI, @RequestParam("password") String newPassword) {

		String url = request.getRequestURI();

		Optional<UrlDataModel> modelOptional = randomUrlService.getModelFromUrl(url);

		if(modelOptional.isEmpty()) {
			return new ResponseEntity<>("Error, unknown url.", HttpStatus.NOT_FOUND);
		}

		UrlDataModel model = modelOptional.get();
		modelUI.addAttribute("user", model.getUserDetails().get());

		switch(model.getAction()) {
			case NEW_PASSWORD:

				passwordResetService.resetPassword(model.getUserDetails().get(), newPassword);

				randomUrlService.removeDataModel(url);
				randomUrlService.removeDataModel(model.getExtra().get());

				return new ResponseEntity<>("Password reset.", HttpStatus.NO_CONTENT);

			default:

				return new ResponseEntity<>("Not found.", HttpStatus.NOT_FOUND);
		}


	}

}
