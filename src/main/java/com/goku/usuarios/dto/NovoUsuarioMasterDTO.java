package com.goku.usuarios.dto;

import javax.validation.constraints.NotBlank;

public class NovoUsuarioMasterDTO {

	@NotBlank(message = "{campo.obrigatorio.login}")
	private String login;

	@NotBlank(message = "{campo.obrigatorio.senha}")
	private String senha;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
