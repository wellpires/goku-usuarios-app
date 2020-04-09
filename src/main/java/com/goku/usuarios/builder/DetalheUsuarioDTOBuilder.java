package com.goku.usuarios.builder;

import com.goku.usuarios.dto.DetalheUsuarioDTO;

public class DetalheUsuarioDTOBuilder {

	private String login;
	private String senha;

	public DetalheUsuarioDTOBuilder login(String login) {
		this.login = login;
		return this;
	}

	public DetalheUsuarioDTOBuilder senha(String senha) {
		this.senha = senha;
		return this;
	}

	public DetalheUsuarioDTO build() {
		DetalheUsuarioDTO detalheUsuarioDTO = new DetalheUsuarioDTO();
		detalheUsuarioDTO.setLogin(login);
		detalheUsuarioDTO.setSenha(senha);
		return detalheUsuarioDTO;
	}

}
