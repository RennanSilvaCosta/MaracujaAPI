package com.usbinternet.apimaracuja.resources;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.usbinternet.apimaracuja.domain.Usuario;
import com.usbinternet.apimaracuja.dto.EmailDTO;
import com.usbinternet.apimaracuja.security.JWTUtil;
import com.usbinternet.apimaracuja.security.UserSS;
import com.usbinternet.apimaracuja.services.AuthService;
import com.usbinternet.apimaracuja.services.UsuarioService;
import com.usbinternet.apimaracuja.services.exceptions.AuthorizationException;

@RestController
@RequestMapping(value = "/auth")
public class AuthResource {

	@Autowired
	private JWTUtil jwtUtil;

	@Autowired
	private UsuarioService userService;
	
	@Autowired
	private AuthService authService;
	

	@RequestMapping(value = "/refresh_token", method = RequestMethod.POST)
	public ResponseEntity<Void> refreshToken(HttpServletResponse response) {
		UserSS user = UsuarioService.authenticated();
		String token = jwtUtil.generateToken(user.getUsername());
		response.addHeader("Authorization", "Bearer " + token);
		response.addHeader("access-control-expose-headers", "Authorization");
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/user_auth", method = RequestMethod.GET)
	public ResponseEntity<Usuario> userAuth() {
		UserSS userss = UsuarioService.authenticated();
		if (userss == null) {
			throw new AuthorizationException("Acesso negado");
		}
		Usuario user = userService.findById(userss.getId());
		return ResponseEntity.ok().body(user);
	}
	
	@RequestMapping(value = "/forgot", method = RequestMethod.POST)
	public ResponseEntity<Usuario> forgot(@Valid  @RequestBody EmailDTO emailDTO) {
		authService.sendNewPassword(emailDTO.getEmail());
		return ResponseEntity.noContent().build();
	}

}
