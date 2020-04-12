package com.goku.usuarios.dto;

import com.goku.usuarios.annotation.EditarUsuario;

@EditarUsuario(message = "{form.obrigatorio.editar-usuario}")
public class EditarUsuarioDTO {

	private String senha;

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
