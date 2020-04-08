package com.goku.usuarios.builder;

import com.goku.usuarios.model.Usuario;

public class UsuarioBuilder {

	private String login;
	private String senha;

	public UsuarioBuilder login(String login) {
		this.login = login;
		return this;
	}

	public UsuarioBuilder senha(String senha) {
		this.senha = senha;
		return this;
	}

	public Usuario build() {
		Usuario usuario = new Usuario();
		usuario.setLogin(login);
		usuario.setSenha(senha);
		return usuario;
	}
}
