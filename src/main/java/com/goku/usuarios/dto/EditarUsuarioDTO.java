package com.goku.usuarios.dto;

import javax.validation.constraints.NotBlank;

public class EditarUsuarioDTO {

	@NotBlank(message = "{campo.obrigatorio.senha}")
	private String senha;

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
