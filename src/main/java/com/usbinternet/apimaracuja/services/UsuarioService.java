package com.usbinternet.apimaracuja.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.usbinternet.apimaracuja.domain.Usuario;
import com.usbinternet.apimaracuja.domain.enums.PerfilUsuario;
import com.usbinternet.apimaracuja.repositories.UsuarioRepository;
import com.usbinternet.apimaracuja.security.UserSS;
import com.usbinternet.apimaracuja.services.exceptions.AuthorizationException;
import com.usbinternet.apimaracuja.services.exceptions.DataIntegrityException;
import com.usbinternet.apimaracuja.services.exceptions.ObjectNotFoundException;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository er;
	
	@Autowired
	private EmailService emailService;

	public Usuario findById(Integer id) {
		Optional<Usuario> e = er.findById(id);
		if (e == null) {
			throw new ObjectNotFoundException("Objeto não encontrado: " + id + ", Tipo: " + Usuario.class.getName());
		}
		return e.orElse(null);
	}

	public Usuario findByEmail(String email) {

		UserSS userss = UsuarioService.authenticated();
		if (userss == null || !userss.hasRole(PerfilUsuario.ADMIN) && !email.equals(userss.getUsername())) {
			throw new AuthorizationException("Acesso negado");
		}
		Usuario user = er.findByEmail(email);
		if (user == null) {
			throw new ObjectNotFoundException(
					"Objeto não encontrado! Id: " + user.getId() + ", Tipo: " + Usuario.class.getName());
		}
		return user;
	}

	public Usuario insert(Usuario user) {
		emailService.sendRegistrationConfirmationEmail(user);
		return er.save(user);
	}

	public Usuario update(Usuario e) {
		findById(e.getId());
		return er.save(e);
	}

	public void delete(Integer id) {
		er.findById(id);
		try {
			er.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não foi possível excluir este usuario");
		}

	}

	public List<Usuario> findAll() {
		return er.findAll();
	}

	public static UserSS authenticated() {
		try {
			return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (Exception e) {
			return null;
		}
	}

}
