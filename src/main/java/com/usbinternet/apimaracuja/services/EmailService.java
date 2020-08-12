package com.usbinternet.apimaracuja.services;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;

import com.usbinternet.apimaracuja.domain.Usuario;

public interface EmailService {

	void sendRegistrationConfirmationEmail(Usuario user);
	
	void sendRegistrationConfirmationHtmlEmail(Usuario user);
	
	void sendEmail(SimpleMailMessage msg);
	
	void sendHtmlEmail(MimeMessage msg);
	
}
