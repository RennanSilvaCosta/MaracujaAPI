package com.usbinternet.apimaracuja.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.usbinternet.apimaracuja.domain.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

	@Transactional(readOnly = true)
	Usuario findByEmail(String email);
	
	@Query(value = "SELECT * FROM usuario WHERE email = ?1", nativeQuery = true)
	Usuario thisEmailExist(String email);

}
