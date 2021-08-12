package com.samuraiDigital.adminsystem.web.services;

import java.time.ZonedDateTime;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.samuraiDigital.adminsystem.data.model.UserSecurityDetails;
import com.samuraiDigital.adminsystem.data.repositories.UserSecurityDetailsRepository;
import com.samuraiDigital.adminsystem.email.services.EmailService;
import com.samuraiDigital.adminsystem.web.model.UrlDataAction;
import com.samuraiDigital.adminsystem.web.model.UrlDataModel;

import net.minidev.json.JSONObject;

@Service
public class PasswordResetServiceImpl implements PasswordResetService {

	private RandomUrlService randomUrlService;
	private UserSecurityDetailsRepository detailsRepository;
	private EmailService emailService;
	private PasswordEncoder encoder;

	public PasswordResetServiceImpl(RandomUrlService randomUrlService, UserSecurityDetailsRepository detailsRepository, EmailService emailService, PasswordEncoder encoder) {
		super();
		this.randomUrlService = randomUrlService;
		this.detailsRepository = detailsRepository;
		this.emailService = emailService;
		this.encoder = encoder;
	}

	private String getPreparedMail(UserSecurityDetails user, String randomUrl) {

		String correctUrl = "http://localhost:8080" + randomUrl;
		JSONObject jsonObject = new JSONObject();
		jsonObject.appendField("from", "no-reply@adminsystem.com");
		jsonObject.appendField("to", user.getEmail());
		jsonObject.appendField("text", String.format("Hello, %s.\nYou have requested an email change, please click on this link to change your password.\nLink: %s", user.getUsername(), correctUrl));
		jsonObject.appendField("subject", "Password reset");

		return jsonObject.toJSONString();
	}

	@Override
	public void requestPasswordReset(String email) {

		Optional<UserSecurityDetails> optionalUser = detailsRepository.findByEmail(email);

		if (optionalUser.isEmpty())
			return;

		UserSecurityDetails user = optionalUser.get();
		String randomUrl = randomUrlService.getRandomUrl();

		UrlDataModel model = new UrlDataModel();
		model.setUserDetails(user);
		model.setAction(UrlDataAction.RESET_PASSWORD);

		randomUrlService.bindModelToUrl(randomUrl, model);
		try {
			emailService.sendEmail(getPreparedMail(user, randomUrl));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void resetPassword(UserSecurityDetails user, String newPassword) {

		user.setPasswordHash(encoder.encode(newPassword));
		user.setCredentialsExpirationDate(ZonedDateTime.now().plusMonths(1));
		detailsRepository.save(user);

	}

}
