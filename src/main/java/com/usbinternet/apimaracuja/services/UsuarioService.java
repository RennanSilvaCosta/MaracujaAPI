package com.usbinternet.apimaracuja.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.usbinternet.apimaracuja.domain.Usuario;
import com.usbinternet.apimaracuja.repositories.UsuarioRepository;
import com.usbinternet.apimaracuja.security.UserSS;
import com.usbinternet.apimaracuja.services.exceptions.DataIntegrityException;
import com.usbinternet.apimaracuja.services.exceptions.ObjectNotFoundException;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private EmailService emailService;

	public Usuario findById(Integer id) {
		Optional<Usuario> e = usuarioRepository.findById(id);
		if (e == null) {
			throw new ObjectNotFoundException("Objeto não encontrado: " + id + ", Tipo: " + Usuario.class.getName());
		}
		return e.orElse(null);
	}

	public Usuario insert(Usuario user) {
		emailService.sendRegistrationConfirmationHtmlEmail(user);
		return usuarioRepository.save(user);
	}

	public Usuario update(Usuario e) {
		findById(e.getId());
		return usuarioRepository.save(e);
	}

	public void delete(Integer id) {
		usuarioRepository.findById(id);
		try {
			usuarioRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não foi possível excluir este usuario");
		}

	}

	public List<Usuario> findAll() {
		return usuarioRepository.findAll();
	}

	public static UserSS authenticated() {
		try {
			return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (Exception e) {
			return null;
		}
	}

	public boolean thisEmailExist(String email) {
		Usuario user = usuarioRepository.thisEmailExist(email);
		if (user != null) {
			return true;
		} else {
			return false;
		}

	}

}
