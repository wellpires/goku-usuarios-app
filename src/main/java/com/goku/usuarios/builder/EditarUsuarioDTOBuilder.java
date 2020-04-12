package com.goku.usuarios.builder;

import com.goku.usuarios.dto.EditarUsuarioDTO;

public class EditarUsuarioDTOBuilder {

	private String senha;

	public EditarUsuarioDTOBuilder senha(String senha) {
		this.senha = senha;
		return this;
	}

	public EditarUsuarioDTO build() {
		EditarUsuarioDTO editarUsuarioDTO = new EditarUsuarioDTO();
		editarUsuarioDTO.setSenha(senha);
		return editarUsuarioDTO;
	}

}
