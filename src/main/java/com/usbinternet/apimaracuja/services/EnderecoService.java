package com.usbinternet.apimaracuja.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.usbinternet.apimaracuja.domain.Endereco;
import com.usbinternet.apimaracuja.repositories.EnderecoRepository;
import com.usbinternet.apimaracuja.services.exceptions.DataIntegrityException;
import com.usbinternet.apimaracuja.services.exceptions.ObjectNotFoundException;

@Service
public class EnderecoService {

	@Autowired
	private EnderecoRepository er;

	public Endereco buscarPorId(Integer id) {
		Optional<Endereco> e = er.findById(id);
		if (e == null) {
			throw new ObjectNotFoundException("Objeto não encontrado: " + id + ", Tipo: " + Endereco.class.getName());
		}
		return e.orElse(null);
	}

	public Endereco insert(Endereco e) {
		e.setId(null);
		return er.save(e);
	}

	public Endereco update(Endereco e) {
		buscarPorId(e.getId());
		return er.save(e);
	}

	public void delete(Integer id) {
		er.findById(id);
		try {
			er.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não foi possivel deletar o CEP informado");
		}
	}

	public Endereco findByCep(String cep, Integer idEmpresa) {
		return er.findCepByIdEmpresa(cep, idEmpresa);
	}

	public List<Endereco> findAll(Integer idEmpresa) {
		return er.getAll(idEmpresa);
	}

}
