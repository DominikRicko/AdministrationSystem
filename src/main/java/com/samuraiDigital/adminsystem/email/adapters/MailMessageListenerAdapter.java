package com.samuraiDigital.adminsystem.email.adapters;

import javax.mail.internet.MimeMessage;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.rabbitmq.client.Channel;
import com.samuraiDigital.adminsystem.email.model.MailMessageModel;

import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;

@Component
public class MailMessageListenerAdapter extends MessageListenerAdapter {

	private JavaMailSender mailSender;

	public MailMessageListenerAdapter(JavaMailSender mailSender) {
		super();
		this.mailSender = mailSender;
	}

	private MailMessageModel convertToMessageModel(Message rabbitMQMessage) throws ParseException {
		JSONParser parser = new JSONParser(JSONParser.DEFAULT_PERMISSIVE_MODE);
		JSONObject jsonObject = new JSONObject();

		String messageBody = new String(rabbitMQMessage.getBody());

		jsonObject.merge(parser.parse(messageBody));

		MailMessageModel mailMessageModel = new MailMessageModel();

		mailMessageModel.setFrom(jsonObject.getAsString("from"));
		mailMessageModel.setTo(jsonObject.getAsString("to"));
		mailMessageModel.setSubject(jsonObject.getAsString("subject"));
		mailMessageModel.setText(jsonObject.getAsString("text"));

		return mailMessageModel;
	}

	@Override
	public void onMessage(Message message, Channel channel) throws Exception {

		try {

			MailMessageModel mailMessageModel = convertToMessageModel(message);

			sendHtmlMail(mailMessageModel);

			// manual ACK
			channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void sendHtmlMail(MailMessageModel messageModel) throws Exception {
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
		mimeMessageHelper.setFrom(messageModel.getFrom());
		mimeMessageHelper.setTo(messageModel.getTo());
		mimeMessageHelper.setSubject(messageModel.getSubject());
		mimeMessageHelper.setText(messageModel.getText(), true);
		// send email
		mailSender.send(mimeMessage);
	}
}