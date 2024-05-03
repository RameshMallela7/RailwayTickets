package com.springboot.RailwayTicket.mail;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;


@Component
public class SendMail extends JavaMailSenderImpl{


	public boolean emailIsValid(String email) throws Exception {
		try {
			EmailValidator internetAddress = EmailValidator.getInstance();
			return internetAddress.isValid(email);
		}catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

}
