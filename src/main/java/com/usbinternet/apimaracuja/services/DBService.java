package com.usbinternet.apimaracuja.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.usbinternet.apimaracuja.domain.Empresa;
import com.usbinternet.apimaracuja.domain.Endereco;
import com.usbinternet.apimaracuja.domain.Usuario;
import com.usbinternet.apimaracuja.domain.enums.PerfilUsuario;
import com.usbinternet.apimaracuja.repositories.EmpresaRepository;
import com.usbinternet.apimaracuja.repositories.EnderecoRepository;
import com.usbinternet.apimaracuja.repositories.UsuarioRepository;

@Service
public class DBService {

	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private UsuarioRepository userRepository;
	@Autowired
	private EmpresaRepository empresaRepository;
	@Autowired
	private BCryptPasswordEncoder pe;

	public void instantiateDevDataBase() {

		Empresa empresa1 = new Empresa(null, "Maracujá");

		Usuario user1 = new Usuario(null, "Rennan Costa", "rennan71@hotmail.com", pe.encode("123"), empresa1);

		user1.addPerfil(PerfilUsuario.ADMIN);
		
		Endereco e1 = new Endereco(null, "00000000", "Abadie Faria Rosa", "Alto da Riviera", empresa1);
		Endereco e2 = new Endereco(null, "00000000", "bla bla bla", "Alto da Riviera", empresa1);

		empresa1.getEnderecos().addAll(Arrays.asList(e1, e2));

		empresaRepository.save(empresa1);
		userRepository.save(user1);
		enderecoRepository.save(e1);
		enderecoRepository.save(e2);

	}

}
