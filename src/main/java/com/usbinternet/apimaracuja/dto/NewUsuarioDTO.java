package com.usbinternet.apimaracuja.dto;

public class NewUsuarioDTO {

	private String nomeEmpresa;
	private String nomeUsuario;
	private String email;
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
