package com.goku.usuarios.builder;

import com.goku.usuarios.dto.DetalheUsuarioDTO;

public class DetalheUsuarioDTOBuilder {

	private String login;

	public DetalheUsuarioDTOBuilder login(String login) {
		this.login = login;
		return this;
	}

	public DetalheUsuarioDTO build() {
		DetalheUsuarioDTO detalheUsuarioDTO = new DetalheUsuarioDTO();
		detalheUsuarioDTO.setLogin(login);
		return detalheUsuarioDTO;
	}

}
