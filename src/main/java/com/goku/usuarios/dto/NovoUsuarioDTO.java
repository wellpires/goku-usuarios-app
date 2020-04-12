package com.goku.usuarios.dto;

import javax.validation.constraints.NotBlank;

import com.goku.usuarios.annotation.Permissao;

public class NovoUsuarioDTO {

	@NotBlank(message = "{campo.obrigatorio.login}")
	private String login;

	@NotBlank(message = "{campo.obrigatorio.senha}")
	private String senha;

	@NotBlank(message = "{campo.obrigatorio.permissao}")
	@Permissao(message = "{campo.invalido.permissao_invalida}")
	private String permissao;

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

	public String getPermissao() {
		return permissao;
	}

	public void setPermissao(String permissao) {
		this.permissao = permissao;
	}

}
