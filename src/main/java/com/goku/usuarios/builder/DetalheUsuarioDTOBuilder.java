package com.goku.usuarios.builder;

import com.goku.usuarios.dto.DetalheUsuarioDTO;

public class DetalheUsuarioDTOBuilder {

	private String login;
	private String permissao;

	public DetalheUsuarioDTOBuilder login(String login) {
		this.login = login;
		return this;
	}

	public DetalheUsuarioDTOBuilder permissao(String permissao) {
		this.permissao = permissao;
		return this;
	}

	public DetalheUsuarioDTO build() {
		DetalheUsuarioDTO detalheUsuarioDTO = new DetalheUsuarioDTO();
		detalheUsuarioDTO.setLogin(login);
		detalheUsuarioDTO.setPermissao(permissao);
		return detalheUsuarioDTO;
	}

}
