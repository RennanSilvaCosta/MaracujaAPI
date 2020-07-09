package com.usbinternet.apimaracuja.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usbinternet.apimaracuja.domain.Endereco;
import com.usbinternet.apimaracuja.domain.Usuario;
import com.usbinternet.apimaracuja.repositories.UsuarioRepository;
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

}
