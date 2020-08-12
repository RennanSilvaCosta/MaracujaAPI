package com.usbinternet.apimaracuja.services;

import java.util.List;
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
	private EmpresaRepository er;

	public Empresa findById(Integer id) {
		Optional<Empresa> e = er.findById(id);
		if (e == null) {
			throw new ObjectNotFoundException("Objeto não encontrado: " + id + ", Tipo: " + Empresa.class.getName());
		}
		return e.orElse(null);
	}

	public Empresa insert(Empresa e) {
		e.setId(null);
		return er.save(e);
	}

	public Empresa update(Empresa e) {
		findById(e.getId());
		return er.save(e);
	}

	public void delete(Integer id) {
		er.findById(id);
		try {
			er.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não foi possivel deletar a empresa informado");
		}
	}

	public List<Empresa> findAll() {
		return er.findAll();
	}

}
