package com.samuraiDigital.adminsystem.web.services;

import java.time.ZonedDateTime;

import org.springframework.stereotype.Service;

import com.samuraiDigital.adminsystem.data.model.UserSecurityDetails;
import com.samuraiDigital.adminsystem.data.repositories.UserSecurityDetailsRepository;
import com.samuraiDigital.adminsystem.email.services.EmailService;
import com.samuraiDigital.adminsystem.web.model.UrlDataAction;
import com.samuraiDigital.adminsystem.web.model.UrlDataModel;

import net.minidev.json.JSONObject;

@Service
public class RegistrationConfirmationServiceImpl implements RegistrationConfirmationService {

	private EmailService emailService;
	private RandomUrlService randomUrlService;
	private UserSecurityDetailsRepository detailsRepository;

	public RegistrationConfirmationServiceImpl(EmailService emailService, RandomUrlService randomUrlService, UserSecurityDetailsRepository detailsRepository) {
		super();
		this.emailService = emailService;
		this.randomUrlService = randomUrlService;
		this.detailsRepository = detailsRepository;
	}

	private String getPreparedEmail(UserSecurityDetails user, String randomUrl) {

		JSONObject jsonObject = new JSONObject();

		String correctUrl = "http://localhost:8080" + randomUrl;
		jsonObject.appendField("to", user.getEmail());
		jsonObject.appendField("from", "no-reply@adminsystem.com");
		jsonObject.appendField("subject", "Registration");
		jsonObject.appendField("text", String.format("Hi %s,\nto complete your registration click on the following link: %s", user.getUsername(), correctUrl));

		return jsonObject.toJSONString();

	}

	@Override
	public void requestConfirmation(UserSecurityDetails user) {

		String randomUrl = randomUrlService.getRandomUrl();

		UrlDataModel model = new UrlDataModel();
		model.setAction(UrlDataAction.REGISTRATION_CONFIRMATION);
		model.setUserDetails(user);

		randomUrlService.bindModelToUrl(randomUrl, model);

		try {
			emailService.sendEmail(getPreparedEmail(user, randomUrl));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void confirmRegistration(UserSecurityDetails user) {

		user.setEnabled(true);
		user.setAccountExpirationDate(ZonedDateTime.now().plusMonths(1));
		detailsRepository.save(user);

	}


}
