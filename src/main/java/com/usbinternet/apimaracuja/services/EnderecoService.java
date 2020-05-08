package com.usbinternet.apimaracuja.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usbinternet.apimaracuja.domain.Endereco;
import com.usbinternet.apimaracuja.repositories.EnderecoRepository;

@Service
public class EnderecoService {

	@Autowired
	private EnderecoRepository er;

	public Endereco buscarPorId(Integer id) {

		Optional<Endereco> e = er.findById(id);
		return e.orElse(null);

	}

}
