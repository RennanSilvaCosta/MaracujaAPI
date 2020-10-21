package com.usbinternet.apimaracuja.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.usbinternet.apimaracuja.domain.Usuario;
import com.usbinternet.apimaracuja.repositories.UsuarioRepository;
import com.usbinternet.apimaracuja.security.UserSS;

@Service
public class UserDetailsServicempl implements UserDetailsService {

	@Autowired
	private UsuarioRepository ur;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Usuario user = ur.findByEmail(email);
		if (user == null) {
			throw new UsernameNotFoundException(email);
		}
		return new UserSS(user.getId(), user.getEmail(), user.getSenha(), user.getPerfil());
	}

}
