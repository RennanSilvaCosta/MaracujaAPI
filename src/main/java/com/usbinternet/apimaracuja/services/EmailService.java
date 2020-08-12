package com.usbinternet.apimaracuja.services;

import org.springframework.mail.SimpleMailMessage;

import com.usbinternet.apimaracuja.domain.Usuario;

public interface EmailService {

	void sendRegistrationConfirmationEmail(Usuario user);
	
	void sendEmail(SimpleMailMessage msg);
	
}
