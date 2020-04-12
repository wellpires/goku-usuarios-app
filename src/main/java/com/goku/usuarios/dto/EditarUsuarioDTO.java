package com.goku.usuarios.dto;

import com.goku.usuarios.annotation.EditarUsuario;
import com.goku.usuarios.annotation.Permissao;

@EditarUsuario(message = "{form.obrigatorio.editar-usuario}")
public class EditarUsuarioDTO {

	private String senha;

	@Permissao(message = "{campo.invalido.permissao_invalida}")
	private String permissao;

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getPermissao() {
		return permissao;
	}

	public void setPermissao(String permissao) {
		this.permissao = permissao;
	}

}
