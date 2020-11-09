package com.usbinternet.apimaracuja.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.text.ParseException;
import com.usbinternet.apimaracuja.services.EmailService;
import com.usbinternet.apimaracuja.services.SmtpEmailService;

@Configuration
@Profile("prod")
public class ProdConfig {

	@Bean
	 public boolean instantiateDataBase() throws ParseException {
	 return true;
	 }
	 
	 @Bean
	 public EmailService emailService() {
	 return new SmtpEmailService();
	 }
	
}
