package com.usbinternet.apimaracuja.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sun.net.httpserver.Authenticator.Success;
import com.usbinternet.apimaracuja.domain.Endereco;
import com.usbinternet.apimaracuja.dto.NewEnderecoDTO;
import com.usbinternet.apimaracuja.services.EnderecoService;

@RestController
@RequestMapping(value = "/enderecos")
public class EnderecoResource {

	@Autowired
	private EnderecoService es;

	@RequestMapping(value = "/{cep}/{idEmpresa}", method = RequestMethod.GET)
	public ResponseEntity<Endereco> find(@PathVariable String cep, @PathVariable Integer idEmpresa) {
		Endereco end = es.findByCep(cep, idEmpresa);
		return ResponseEntity.ok().body(end);
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> insert(@RequestBody NewEnderecoDTO dto) {
		Endereco end = new Endereco();
		end.setCep(dto.getCep());
		end.setLogradouro(dto.getLogradouro());
		end.setBairro(dto.getBairro());
		end.setEmpresa(dto.getEmpresa());
		es.insert(end);
		return new ResponseEntity<Success>(HttpStatus.OK);
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody Endereco e, @PathVariable Integer id) {
		e.setId(id);
		e = es.update(e);
		return ResponseEntity.noContent().build();
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		es.delete(id);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/{idEmpresa}", method = RequestMethod.GET)
	public ResponseEntity<List<Endereco>> findAll(@PathVariable Integer idEmpresa) {
		List<Endereco> lista = es.findAll(idEmpresa);
		return ResponseEntity.ok().body(lista);
	}

}
