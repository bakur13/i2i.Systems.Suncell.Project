package com.suncell.rest.api.mail;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

public class MailSender {

	  @Autowired
	    private JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

	    public void sendEmail(String toEmail, String subject, String body) {
	        mailSender.setHost("smtp.gmail.com");
	        mailSender.setPort(587);
	        mailSender.setUsername("1234567mustafa.mkk@gmail.com");
	        mailSender.setPassword("nnfyafxbilhgthim");
	        Properties props = mailSender.getJavaMailProperties();
	        props.put("mail.transport.protocol", "smtp");
	        props.put("mail.smtp.auth", "true");
	        props.put("mail.smtp.starttls.enable", "true");
	        props.put("mail.debug", "true");
	        SimpleMailMessage message = new SimpleMailMessage();
	        message.setTo(toEmail);
	        message.setSubject(subject);
	        message.setText(body);
	        mailSender.send(message);
	    }
}
