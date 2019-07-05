package com.alan.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.alan.model.Emp;
import com.alan.model.UserDTO;

@Service
public class MailService {
	@Autowired
	private JavaMailSender sender;
	
	public String autoSendingEmail(Emp emp, UserDTO user) {
		MimeMessage msg = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(msg);
		try {
			helper.setTo(user.getUsername());
			String content = 
					"Welcome to on-Board into E-office Corp.\n" + 
					"Welcome Mr " + emp.getF_Name()+ ", your details as below:-\n" + 
					"empId:" + emp.getEmpId() + " \n" + 
					"Loginid:" + user.getUsername() +" \n" + 
					"Password:" + user.getPassword() + " from Users\n" ;
			helper.setText(content);
			helper.setSubject("welcome on-board Mr " + emp.getF_Name() + " " + emp.getL_Name());
		} catch (MessagingException e) {
			e.printStackTrace();
			return "Exception while sending email...";
		}
		sender.send(msg);
		return "mail sent successfully!";
	}
	
	public String sendingEmailAttachment() throws MessagingException{
		MimeMessage msg = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(msg,true);
		try {
			helper.setTo("ofcadmncog@gmail.com");
			helper.setText("welcome to STS att");
			helper.setSubject("email sts att");
			ClassPathResource file = new ClassPathResource("en.jpg");
			helper.addAttachment("en.jpg", file);
		} catch (MessagingException e) {
			e.printStackTrace();
			return "Exception while sending email... att";
		}
		sender.send(msg);
		return "mail attachment sent successfully";
	}
}
