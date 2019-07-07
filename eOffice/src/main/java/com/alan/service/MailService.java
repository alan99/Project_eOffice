package com.alan.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class MailService {
	@Autowired
	private JavaMailSender sender;
	
	public String autoSendingEmail(String receiver, String subject, String content) {
		MimeMessage msg = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(msg);
		try {
			helper.setTo(receiver);
			helper.setSubject(subject);
			helper.setText(content);
		} catch (MessagingException e) {
			e.printStackTrace();
			return "Exception while sending email...";
		}
		sender.send(msg);
		return "Mail sent successfully!!!";
	}
	
//	public String sendingEmailAttachment() throws MessagingException{
//		MimeMessage msg = sender.createMimeMessage();
//		MimeMessageHelper helper = new MimeMessageHelper(msg,true);
//		try {
//			helper.setTo("ofcadmncog@gmail.com");
//			helper.setText("welcome to STS att");
//			helper.setSubject("email sts att");
//			ClassPathResource file = new ClassPathResource("en.jpg");
//			helper.addAttachment("en.jpg", file);
//		} catch (MessagingException e) {
//			e.printStackTrace();
//			return "Exception while sending email... att";
//		}
//		sender.send(msg);
//		return "mail attachment sent successfully";
//	}
}
