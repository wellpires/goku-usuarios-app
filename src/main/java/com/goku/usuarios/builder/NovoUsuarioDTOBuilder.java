package com.goku.usuarios.builder;

import com.goku.usuarios.dto.NovoUsuarioDTO;
import com.goku.usuarios.enums.Permissao;

public class NovoUsuarioDTOBuilder {

	private String login;
	private String senha;
	private String permissao;

	public NovoUsuarioDTOBuilder login(String login) {
		this.login = login;
		return this;
	}

	public NovoUsuarioDTOBuilder senha(String senha) {
		this.senha = senha;
		return this;
	}

	public NovoUsuarioDTOBuilder permissao(Permissao permissao) {
		this.permissao = permissao.name();
		return this;
	}

	public NovoUsuarioDTOBuilder permissao(String permissao) {
		this.permissao = permissao.toUpperCase();
		return this;
	}

	public NovoUsuarioDTO build() {
		NovoUsuarioDTO novoUsuarioDTO = new NovoUsuarioDTO();
		novoUsuarioDTO.setLogin(login);
		novoUsuarioDTO.setSenha(senha);
		novoUsuarioDTO.setPermissao(permissao);
		return novoUsuarioDTO;
	}

}
