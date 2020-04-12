package com.goku.usuarios.builder;

import com.goku.usuarios.dto.NovoUsuarioMasterDTO;

public class NovoUsuarioMasterDTOBuilder {

	private String login;
	private String senha;

	public NovoUsuarioMasterDTOBuilder login(String login) {
		this.login = login;
		return this;
	}

	public NovoUsuarioMasterDTOBuilder senha(String senha) {
		this.senha = senha;
		return this;
	}

	public NovoUsuarioMasterDTO build() {
		NovoUsuarioMasterDTO novoUsuarioMasterDTO = new NovoUsuarioMasterDTO();
		novoUsuarioMasterDTO.setLogin(login);
		novoUsuarioMasterDTO.setSenha(senha);
		return novoUsuarioMasterDTO;
	}

}
