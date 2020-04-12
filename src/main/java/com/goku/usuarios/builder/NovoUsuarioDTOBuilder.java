package com.goku.usuarios.builder;

import com.goku.usuarios.dto.NovoUsuarioDTO;

public class NovoUsuarioDTOBuilder {

	private String login;
	private String senha;

	public NovoUsuarioDTOBuilder login(String login) {
		this.login = login;
		return this;
	}

	public NovoUsuarioDTOBuilder senha(String senha) {
		this.senha = senha;
		return this;
	}

	public NovoUsuarioDTO build() {
		NovoUsuarioDTO novoUsuarioDTO = new NovoUsuarioDTO();
		novoUsuarioDTO.setLogin(login);
		novoUsuarioDTO.setSenha(senha);
		return novoUsuarioDTO;
	}

}
