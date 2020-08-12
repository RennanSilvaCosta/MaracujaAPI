package com.usbinternet.apimaracuja.services;

import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.usbinternet.apimaracuja.domain.Usuario;

public abstract class AbstractEmailService implements EmailService {

	@Value("${default.sender}")
	private String sender;

	@Autowired
	private TemplateEngine templateEngine;

	@Autowired
	private JavaMailSender javaMailSender;

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

	protected String htmlFromTemplateUsuario(Usuario user) {
		Context context = new Context();
		context.setVariable("usuario", user);
		return templateEngine.process("email/confirmacaoNovoUsuario", context);
	}

	@Override
	public void sendRegistrationConfirmationHtmlEmail(Usuario user) {
		MimeMessage mm;
		try {
			mm = prepareMimeMessageFromUsuario(user);
			sendHtmlEmail(mm);
		} catch (MessagingException e) {
			sendRegistrationConfirmationEmail(user);
		}

	}

	private MimeMessage prepareMimeMessageFromUsuario(Usuario user) throws MessagingException {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper mmh = new MimeMessageHelper(mimeMessage, true);
		mmh.setTo(user.getEmail());
		mmh.setFrom(sender);
		mmh.setSubject("Conta criada com sucesso!");
		mmh.setSentDate(new Date(System.currentTimeMillis()));
		mmh.setText(htmlFromTemplateUsuario(user), true);
		return mimeMessage;
	}

}
