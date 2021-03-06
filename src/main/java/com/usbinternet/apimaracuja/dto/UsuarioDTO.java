package com.usbinternet.apimaracuja.dto;

import java.io.Serializable;

import com.usbinternet.apimaracuja.domain.Usuario;

public class UsuarioDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String nome;
	private String email;
	private String senha;

	public UsuarioDTO() {
	}

	public UsuarioDTO(Usuario user) {
		id = user.getId();
		nome = user.getNome();
		email = user.getEmail();
		senha = user.getSenha();
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
