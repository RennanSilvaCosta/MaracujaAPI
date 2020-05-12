package com.usbinternet.apimaracuja.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usbinternet.apimaracuja.domain.Endereco;
import com.usbinternet.apimaracuja.repositories.EnderecoRepository;

@Service
public class DBService {

	@Autowired
	private EnderecoRepository enderecoRepository;

	public void instantiateDevDataBase() {

		Endereco e = new Endereco(null, "04929080", "Avenida Professor Mário Mazagão", "Alto da Riviera");
		Endereco e1 = new Endereco(null, "04929290", "Ricardina Viêira Coelho", "Alto da Riviera");
		Endereco e2 = new Endereco(null, "04929210", "Luís Teixeira de Oliveira", "Alto da Riviera");

		enderecoRepository.save(e);
		enderecoRepository.save(e1);
		enderecoRepository.save(e2);

	}

}
