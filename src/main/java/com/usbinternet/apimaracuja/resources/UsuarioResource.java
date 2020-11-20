package com.usbinternet.apimaracuja.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.usbinternet.apimaracuja.domain.Empresa;
import com.usbinternet.apimaracuja.domain.Usuario;
import com.usbinternet.apimaracuja.domain.enums.PerfilUsuario;
import com.usbinternet.apimaracuja.dto.NewUsuarioDTO;
import com.usbinternet.apimaracuja.dto.UsuarioDTO;

import com.usbinternet.apimaracuja.services.EmpresaService;
import com.usbinternet.apimaracuja.services.UsuarioService;
import com.usbinternet.apimaracuja.services.exceptions.DataIntegrityException;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioResource {

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private EmpresaService empresaService;

	@Autowired
	private BCryptPasswordEncoder pe;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Usuario> find(@PathVariable Integer id) {
		Usuario e = usuarioService.findById(id);
		return ResponseEntity.ok().body(e);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> insert(@RequestBody NewUsuarioDTO newUsuarioDTO) {
		if (usuarioService.thisEmailExist(newUsuarioDTO.getEmail())) {
			throw new DataIntegrityException("Email informado já está cadastrado!");
		} else {
			Empresa empresa = new Empresa(null, newUsuarioDTO.getNomeEmpresa());
			Usuario user = new Usuario(null, newUsuarioDTO.getNomeUsuario(), newUsuarioDTO.getEmail(),
					pe.encode(newUsuarioDTO.getSenha()), empresa);
			user.addPerfil(PerfilUsuario.ADMIN);
			empresa.getUsuarios().add(user);
			empresaService.insert(empresa);
			usuarioService.insert(user);
			return ResponseEntity.noContent().build();
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody Usuario e, @PathVariable Integer id) {
		e.setId(id);
		e = usuarioService.update(e);
		return ResponseEntity.noContent().build();
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		usuarioService.delete(id);
		return ResponseEntity.noContent().build();
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<UsuarioDTO>> findAll() {
		List<Usuario> lista = usuarioService.findAll();
		List<UsuarioDTO> listaDto = lista.stream().map(obj -> new UsuarioDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listaDto);
	}

}
