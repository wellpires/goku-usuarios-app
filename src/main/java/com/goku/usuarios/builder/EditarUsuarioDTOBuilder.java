package com.goku.usuarios.builder;

import com.goku.usuarios.dto.EditarUsuarioDTO;
import com.goku.usuarios.enums.Permissao;

public class EditarUsuarioDTOBuilder {

	private String senha;
	private String permissao;

	public EditarUsuarioDTOBuilder senha(String senha) {
		this.senha = senha;
		return this;
	}

	public EditarUsuarioDTOBuilder permissao(Permissao permissao) {
		this.permissao = permissao.name();
		return this;
	}

	public EditarUsuarioDTO build() {
		EditarUsuarioDTO editarUsuarioDTO = new EditarUsuarioDTO();
		editarUsuarioDTO.setSenha(senha);
		editarUsuarioDTO.setPermissao(permissao);
		return editarUsuarioDTO;
	}

}
