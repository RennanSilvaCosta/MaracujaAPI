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
		Empresa empresa2 = new Empresa(null, "Spark");

		Usuario user1 = new Usuario(null, "Rennan Costa", "rennansilvacosta@hotmail.com", pe.encode("rammstein-1"),
				empresa1);

		Usuario user2 = new Usuario(null, "Ester Duarte", "duartelopes@hotmail.com", pe.encode("rammstein-1"), empresa2);
		
		user1.addPerfil(PerfilUsuario.ADMIN);
		user2.addPerfil(PerfilUsuario.ADMIN);
		
		Endereco e1 = new Endereco(null, "04929-080", "Avenida Professor Mário Mazagão", "Alto da Riviera", empresa1);
		Endereco e2 = new Endereco(null, "04716-070", "Alameda das Quaresmeiras", "Chácara Santo Antônio (Zona Sul)", empresa1);
		Endereco e3 = new Endereco(null, "02955-020", "Rua Sousa Sepúlveda", "Vila Mirante", empresa1);
		Endereco e4 = new Endereco(null, "01248-090", "Rua Modigliani", "Pacaembu", empresa1);
		Endereco e5 = new Endereco(null, "04929-140", "Paulo Simões da Costa", "Alto da Riviera", empresa2);
		
		empresa1.getEnderecos().addAll(Arrays.asList(e1));
		empresa1.getEnderecos().addAll(Arrays.asList(e2));
		empresa1.getEnderecos().addAll(Arrays.asList(e3));
		empresa1.getEnderecos().addAll(Arrays.asList(e4));	
		
		empresa2.getEnderecos().addAll(Arrays.asList(e5));

		empresaRepository.save(empresa1);
		empresaRepository.save(empresa2);
		userRepository.save(user1);
		userRepository.save(user2);
		enderecoRepository.save(e1);
		enderecoRepository.save(e2);
		enderecoRepository.save(e3);
		enderecoRepository.save(e4);
		enderecoRepository.save(e5);
	}

}
