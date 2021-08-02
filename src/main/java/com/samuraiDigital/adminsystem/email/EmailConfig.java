package com.samuraiDigital.adminsystem.email;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class EmailConfig {

	private Environment env;

	public EmailConfig(Environment env) {
		super();
		this.env = env;
	}

	@Bean(name = "mailSender")
	public JavaMailSender mailSender() {

		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

		mailSender.setHost(env.getProperty("mail.host").trim());
		mailSender.setPort(Integer.parseInt(env.getProperty("mail.port").trim()));
		mailSender.setUsername(env.getProperty("mail.username").trim());
		mailSender.setPassword(env.getProperty("mail.password").trim());
		mailSender.setDefaultEncoding("utf-8");

		Properties props = new Properties();

		props.put("mail.smtp.auth", true);
		props.put("mail.smtp.timeout", "25000");
		mailSender.setJavaMailProperties(props);

		return mailSender;

	}
}