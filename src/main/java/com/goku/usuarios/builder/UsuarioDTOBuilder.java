package com.goku.usuarios.builder;

import com.goku.usuarios.dto.UsuarioDTO;

public class UsuarioDTOBuilder {

	private String login;

	public UsuarioDTOBuilder login(String login) {
		this.login = login;
		return this;
	}

	public UsuarioDTO build() {
		UsuarioDTO usuarioDTO = new UsuarioDTO();
		usuarioDTO.setLogin(login);
		return usuarioDTO;
	}

}
