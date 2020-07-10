package com.usbinternet.apimaracuja.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.usbinternet.apimaracuja.domain.Usuario;
import com.usbinternet.apimaracuja.repositories.UsuarioRepository;
import com.usbinternet.apimaracuja.services.exceptions.DataIntegrityException;
import com.usbinternet.apimaracuja.services.exceptions.ObjectNotFoundException;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository er;

	public Usuario buscarPorId(Integer id) {
		Optional<Usuario> e = er.findById(id);
		if (e == null) {
			throw new ObjectNotFoundException("Objeto não encontrado: " + id + ", Tipo: " + Usuario.class.getName());
		}
		return e.orElse(null);

	}

	public Usuario insert(Usuario user) {
		user.setId(null);
		return er.save(user);
	}

	public Usuario update(Usuario e) {
		buscarPorId(e.getId());
		return er.save(e);
	}

	public void delete(Integer id) {
		er.findById(id);
		try {
			er.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não ofi possível excluir este usuario");
		}

	}

	public List<Usuario> findAll() {
		return er.findAll();
	}

}
