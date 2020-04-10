package com.goku.usuarios.builder;

import java.util.ArrayList;
import java.util.List;

import com.goku.usuarios.model.Usuario;

public class UsuarioBuilder {

	private String login;
	private String senha;
	private int quantidadeItens;

	public UsuarioBuilder login(String login) {
		this.login = login;
		return this;
	}

	public UsuarioBuilder senha(String senha) {
		this.senha = senha;
		return this;
	}

	public UsuarioBuilder quantidadeItens(int quantidadeItens) {
		this.quantidadeItens = quantidadeItens;
		return this;
	}

	public Usuario build() {
		Usuario usuario = new Usuario();
		usuario.setLogin(login);
		usuario.setSenha(senha);
		return usuario;
	}

	public List<Usuario> buildList() {
		List<Usuario> usuarios = new ArrayList<>();

		for (Integer i = 0; i < quantidadeItens; i++) {
			Usuario usuario = new Usuario();
			usuario.setLogin("usuario ".concat(i.toString()));
			usuario.setSenha("senha ".concat(i.toString()));
			usuarios.add(usuario);
		}

		return usuarios;
	}

}
