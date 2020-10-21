package com.usbinternet.apimaracuja.dto;

import java.io.Serializable;

import com.usbinternet.apimaracuja.domain.Empresa;

public class NewEnderecoDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String cep;
	private String logradouro;
	private String bairro;
	private Empresa empresa;

	public NewEnderecoDTO() {

	}

	public NewEnderecoDTO(String cep, String logradouro, String bairro, Empresa empresa) {
		super();
		this.cep = cep;
		this.logradouro = logradouro;
		this.bairro = bairro;
		this.empresa = empresa;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

}
