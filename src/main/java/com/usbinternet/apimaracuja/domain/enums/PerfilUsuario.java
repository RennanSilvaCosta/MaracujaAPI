package com.usbinternet.apimaracuja.domain.enums;

public enum PerfilUsuario {

	ADMIN(1, "ROLE_ADMIN"), OTHER_USER(2, "ROLE_OTHER_USER");

	private int cod;
	private String descricao;

	private PerfilUsuario(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public int getCod() {
		return cod;
	}

	public void setCod(int cod) {
		this.cod = cod;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public static PerfilUsuario toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}
		for (PerfilUsuario x : PerfilUsuario.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}
		}

		throw new IllegalArgumentException("ID inválido: " + cod);

	}

}
