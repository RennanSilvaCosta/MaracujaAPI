package com.usbinternet.apimaracuja.dto;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

public class NewUsuarioDTO {

	@NotEmpty(message="Nome da empresa não pode ser nulo.")
	@Length(min=5, max=80, message="O nome da empresa deve ter no minimo 5 caracteres.")
	private String nomeEmpresa;
	
	@NotEmpty(message="Nome de usuario não pode ser nulo.")
	@Length(min=5, max=80, message="O nome de usuario deve conter no minimo 5 caracteres.")
	private String nomeUsuario;
	
	@NotEmpty(message="O email não pode ser nulo.")
	private String email;
	
	@NotEmpty(message="A senha não pode ser nula.")
	private String senha;

	public NewUsuarioDTO() {
	}

	public NewUsuarioDTO(String nomeEmpresa, String nomeUsuario, String email, String senha) {
		super();
		this.nomeEmpresa = nomeEmpresa;
		this.nomeUsuario = nomeUsuario;
		this.email = email;
		this.senha = senha;
	}

	public String getNomeEmpresa() {
		return nomeEmpresa;
	}

	public void setNomeEmpresa(String nomeEmpresa) {
		this.nomeEmpresa = nomeEmpresa;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
