package com.usbinternet.apimaracuja.dto;

public class UsuarioAndEmpresaDTO {

	private Integer idEmpresa;
	private Integer idUsuario;
	private String nomeEmpresa;
	private String nomeUsuario;
	private String emailUsuario;

	public UsuarioAndEmpresaDTO() {
	}

	public UsuarioAndEmpresaDTO(Integer idEmpresa, Integer idUsuario, String nomeEmpresa, String nomeUsuario,
			String emailUsuario) {
		super();
		this.idEmpresa = idEmpresa;
		this.idUsuario = idUsuario;
		this.nomeEmpresa = nomeEmpresa;
		this.nomeUsuario = nomeUsuario;
		this.emailUsuario = emailUsuario;
	}

	public Integer getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(Integer idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
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

	public String getEmailUsuario() {
		return emailUsuario;
	}

	public void setEmailUsuario(String emailUsuario) {
		this.emailUsuario = emailUsuario;
	}

}
