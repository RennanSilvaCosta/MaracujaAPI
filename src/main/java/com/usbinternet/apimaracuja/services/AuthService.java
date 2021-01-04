package com.usbinternet.apimaracuja.services;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.usbinternet.apimaracuja.domain.Usuario;
import com.usbinternet.apimaracuja.repositories.UsuarioRepository;
import com.usbinternet.apimaracuja.services.exceptions.ObjectNotFoundException;

@Service
public class AuthService {

	@Autowired
	private BCryptPasswordEncoder pe;

	@Autowired
	private EmailService emailService;

	@Autowired
	private UsuarioRepository usuarioRepository;

	private Random random = new Random();

	public void sendNewPassword(String email) {

		Usuario usuario = usuarioRepository.findByEmail(email);
		if (usuario == null) {
			throw new ObjectNotFoundException("Email não cadastrado!");
		}

		String newPassword = newPassword();
		usuario.setSenha(pe.encode(newPassword));

		usuarioRepository.save(usuario);
		emailService.sendNewPasswordEmail(usuario, newPassword);

	}

	private String newPassword() {
		char[] vet = new char[10];
		for (int i = 0; i < vet.length; i++) {
			vet[i] = randomChar();
		}
		return new String(vet);
	}

	private char randomChar() {
		int opt = random.nextInt(3);
		if (opt == 0) { // gera um digito
			return (char) (random.nextInt(10) + 48);
		} else if (opt == 1) { // gera letra maiuscula
			return (char) (random.nextInt(26) + 65);
		} else { // gera letra minuscula
			return (char) (random.nextInt(26) + 97);
		}
	}

}
