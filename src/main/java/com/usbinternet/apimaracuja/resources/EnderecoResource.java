package com.usbinternet.apimaracuja.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.usbinternet.apimaracuja.domain.Endereco;
import com.usbinternet.apimaracuja.services.EnderecoService;

@RestController
@RequestMapping(value = "/enderecos")
public class EnderecoResource {

	@Autowired
	private EnderecoService es;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Endereco> find(@PathVariable Integer id) {
		Endereco e = es.buscarPorId(id);
		return ResponseEntity.ok().body(e);
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody Endereco e) {
		e = es.insert(e);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(e.getId()).toUri();
		return ResponseEntity.created(uri).build();
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

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Endereco>> findAll() {
		List<Endereco> lista = es.findAll();
		return ResponseEntity.ok().body(lista);
	}

}
