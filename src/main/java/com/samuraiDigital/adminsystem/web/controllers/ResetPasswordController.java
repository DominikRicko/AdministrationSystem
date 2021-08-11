package com.samuraiDigital.adminsystem.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.samuraiDigital.adminsystem.web.services.PasswordResetService;
import com.samuraiDigital.adminsystem.web.services.WebMessageService;

@Controller
public class ResetPasswordController {

	private PasswordResetService passwordResetService;
	private WebMessageService errorService;
	private WebMessageService infoService;

	public ResetPasswordController(PasswordResetService passwordResetService, WebMessageService errorService, WebMessageService infoService) {
		super();
		this.passwordResetService = passwordResetService;
		this.errorService = errorService;
		this.infoService = infoService;
	}

	@RequestMapping(value = "/reset_password", method = RequestMethod.GET)
	public String getPasswordReset() {
		return "pages/reset_password";
	}

	@RequestMapping(value = "/reset_password", method = RequestMethod.POST)
	public String startPasswordReset(@RequestParam("email") String email, RedirectAttributes model) {

		passwordResetService.requestPasswordReset(email);

		infoService.addMessageToModel(model, "Check your email for password reset link, in case you cannot find it, check spam folder, otherwise you sent us a wrong email.");

		return "redirect:/login";
	}

}
