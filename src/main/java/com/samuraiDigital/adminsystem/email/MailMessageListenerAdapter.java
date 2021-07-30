package com.samuraiDigital.adminsystem.email;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.rabbitmq.client.Channel;

import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;

@Component("mailMessageListenerAdapter")
public class MailMessageListenerAdapter extends MessageListenerAdapter {

	@Resource
	private JavaMailSender mailSender;

	@Value("${mail.username}")
	private String mailUsername;

	@Override
	public void onMessage(Message message, Channel channel) throws Exception {

		JSONParser parser = new JSONParser(JSONParser.DEFAULT_PERMISSIVE_MODE);
		JSONObject jsonObject = new JSONObject();

		try {
			// Parse the RabbitMQ message body
			String messageBody = new String(message.getBody());

			jsonObject.merge(parser.parse(messageBody));

			MailMessageModel mailMessageModel = new MailMessageModel();

			mailMessageModel.setFrom(jsonObject.getAsString("from"));
			mailMessageModel.setTo(jsonObject.getAsString("to"));
			mailMessageModel.setSubject(jsonObject.getAsString("subject"));
			mailMessageModel.setText(jsonObject.getAsString("text"));

			// send email
			String to = mailMessageModel.getTo();
			String subject = mailMessageModel.getSubject();
			String text = mailMessageModel.getText();
			sendHtmlMail(to, subject, text);
			// manual ACK
			channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	       * send email 
	 * @param to
	 * @param subject
	 * @param text
	 * @throws Exception
	 */
	private void sendHtmlMail(String to, String subject, String text) throws Exception {
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
		mimeMessageHelper.setFrom(mailUsername);
		mimeMessageHelper.setTo(to);
		mimeMessageHelper.setSubject(subject);
		mimeMessageHelper.setText(text, true);
		// send email
		mailSender.send(mimeMessage);
	}
}