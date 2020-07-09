package com.usbinternet.apimaracuja.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usbinternet.apimaracuja.domain.Endereco;
import com.usbinternet.apimaracuja.domain.Usuario;
import com.usbinternet.apimaracuja.repositories.EnderecoRepository;
import com.usbinternet.apimaracuja.repositories.UsuarioRepository;

@Service
public class DBService {

	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private UsuarioRepository userRepository;

	public void instantiateDevDataBase() {

		Usuario user = new Usuario(null, "EmpresaX", "Empresax@gmail.com");
		Endereco e4 = new Endereco(null, "00000000", "Abadie Faria Rosa", "Alto da Riviera", user);

		user.getEnderecos().addAll(Arrays.asList(e4));

		userRepository.save(user);

		enderecoRepository.save(e4);

	}

}
