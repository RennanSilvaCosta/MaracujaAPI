package com.usbinternet.apimaracuja.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.usbinternet.apimaracuja.domain.Endereco;
import com.usbinternet.apimaracuja.domain.Usuario;
import com.usbinternet.apimaracuja.domain.enums.PerfilUsuario;
import com.usbinternet.apimaracuja.repositories.EnderecoRepository;
import com.usbinternet.apimaracuja.repositories.UsuarioRepository;

@Service
public class DBService {

	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private UsuarioRepository userRepository;
	@Autowired
	private BCryptPasswordEncoder pe;

	public void instantiateDevDataBase() {

		Usuario user = new Usuario(null, "EmpresaX", "Empresax@gmail.com", pe.encode("123456"));
		Usuario user2 = new Usuario(null, "EmpresaY", "rennan@gmail.com", pe.encode("123456"));
		Usuario user3 = new Usuario(null, "Empresa do Rennan", "rennancosta@gmail.com", pe.encode("123456"));
		
		user.addPerfil(PerfilUsuario.OTHER_USER);
		user2.addPerfil(PerfilUsuario.OTHER_USER);
		user3.addPerfil(PerfilUsuario.ADMIN);
		
		Endereco e1 = new Endereco(null, "00000000", "Abadie Faria Rosa", "Alto da Riviera", user);
		Endereco e2 = new Endereco(null, "00000000", "Ricardina Viera Coelho", "Alto da Riviera", user2);

		user.getEnderecos().addAll(Arrays.asList(e1));
		user2.getEnderecos().addAll(Arrays.asList(e2));

		userRepository.save(user);
		userRepository.save(user2);
		userRepository.save(user3);
		
		enderecoRepository.save(e1);
		enderecoRepository.save(e2);

	}

}
