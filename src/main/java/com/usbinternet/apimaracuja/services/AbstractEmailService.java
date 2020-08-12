package com.usbinternet.apimaracuja.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import com.usbinternet.apimaracuja.domain.Usuario;

public abstract class AbstractEmailService implements EmailService{
	
	@Value("${default.sender}")
	private String sender;

	@Override
	public void sendRegistrationConfirmationEmail(Usuario user) {
		SimpleMailMessage sm = prepareSimpleMailMessageFromUsuario(user);
		sendEmail(sm);
	}

	protected SimpleMailMessage prepareSimpleMailMessageFromUsuario(Usuario user) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(user.getEmail());
		sm.setFrom(sender);
		sm.setSubject("Conta criada com sucesso");
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText("Bem vindo " + user.getNome());
		return sm;
	}
	
}
