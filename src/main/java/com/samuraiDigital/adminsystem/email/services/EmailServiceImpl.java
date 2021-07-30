package com.samuraiDigital.adminsystem.email.services;

import javax.annotation.Resource;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

	private static Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);

	private RabbitTemplate rabbitTemplate;
	private String exchange;
	private String routeKey;

	public EmailServiceImpl(RabbitTemplate rabbitTemplate, @Value("${mq.exchange.name}") String exchange,
			@Value("${mq.routekey}") String routeKey) {
		super();
		this.rabbitTemplate = rabbitTemplate;
		this.exchange = exchange;
		this.routeKey = routeKey;
	}

	@Override
	public void sendEmail(String message) throws Exception {

		try {
			rabbitTemplate.convertAndSend(exchange, routeKey, message);
		} catch (Exception e) {
			logger.error("EmailServiceImpl.sendEmail", ExceptionUtils.getMessage(e));
		}

	}

}
