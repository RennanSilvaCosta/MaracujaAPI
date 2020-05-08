package com.usbinternet.apimaracuja.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.usbinternet.apimaracuja.domain.Endereco;

@RestController
@RequestMapping(value = "/enderecos")
public class EnderecoResource {

	@RequestMapping(method = RequestMethod.GET)
	public List<Endereco> listar() {

		Endereco e1 = new Endereco(1, "04929080", "Avenida Professor Mário Mazagão", "Alto da Riviera");
		Endereco e2 = new Endereco(2, "04929290", "Ricardina Viera Coelho", "Alto da Riviera");

		List<Endereco> lista = new ArrayList<>();

		lista.add(e1);
		lista.add(e2);

		return lista;
	}

}
