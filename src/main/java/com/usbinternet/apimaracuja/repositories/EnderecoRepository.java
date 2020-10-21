package com.usbinternet.apimaracuja.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.usbinternet.apimaracuja.domain.Endereco;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Integer>{

	@Query(value = "SELECT * FROM endereco WHERE cep = ?1 and empresa_id = ?2", nativeQuery = true)
	Endereco findCepByIdEmpresa(String cep, Integer idEmpresa);

	@Query(value = "SELECT * FROM endereco WHERE empresa_id = ?", nativeQuery = true)
	List<Endereco> getAll(Integer idEmpresa);
	
}
