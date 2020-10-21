package com.usbinternet.apimaracuja.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.usbinternet.apimaracuja.domain.Empresa;
import com.usbinternet.apimaracuja.repositories.EmpresaRepository;
import com.usbinternet.apimaracuja.services.exceptions.DataIntegrityException;
import com.usbinternet.apimaracuja.services.exceptions.ObjectNotFoundException;

@Service
public class EmpresaService {

	@Autowired
	private EmpresaRepository empresaRepository;
	
	public Empresa findById(Integer id) {
		Optional<Empresa> e = empresaRepository.findById(id);
		if (e == null) {
			throw new ObjectNotFoundException("Objeto não encontrado: " + id + ", Tipo: " + Empresa.class.getName());
		}
		return e.orElse(null);
	}

	public Empresa insert(Empresa e) {
		e.setId(null);
		return empresaRepository.save(e);
	}

	public Empresa update(Empresa e) {
		findById(e.getId());
		return empresaRepository.save(e);
	}

	public void delete(Integer id) {
		empresaRepository.findById(id);
		try {
			empresaRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não foi possivel deletar a empresa informado");
		}
	}
		
}
