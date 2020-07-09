package com.usbinternet.apimaracuja.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.usbinternet.apimaracuja.domain.Usuario;
import com.usbinternet.apimaracuja.services.UsuarioService;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioResource {

	@Autowired
	private UsuarioService us;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Usuario> find(@PathVariable Integer id) {
		Usuario e = us.buscarPorId(id);
		return ResponseEntity.ok().body(e);
	}

	/*@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody Usuario e) {
		e = es.insert(e);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(e.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody Usuario e, @PathVariable Integer id) {
		e.setId(id);
		e = es.update(e);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		es.delete(id);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Usuario>> findAll() {
		List<Usuario> lista = es.findAll();
		return ResponseEntity.ok().body(lista);
	}*/

}
