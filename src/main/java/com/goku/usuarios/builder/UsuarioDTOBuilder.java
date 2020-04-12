package com.goku.usuarios.builder;

import java.util.ArrayList;
import java.util.List;

import com.goku.usuarios.dto.UsuarioDTO;

public class UsuarioDTOBuilder {

	private String login;
	private String permissao;
	private int quantidadeItens;

	public UsuarioDTOBuilder login(String login) {
		this.login = login;
		return this;
	}

	public UsuarioDTOBuilder permissao(String permissao) {
		this.permissao = permissao;
		return this;
	}

	public UsuarioDTOBuilder quantidadeItens(int quantidadeItens) {
		this.quantidadeItens = quantidadeItens;
		return this;
	}

	public UsuarioDTO build() {
		UsuarioDTO usuarioDTO = new UsuarioDTO();
		usuarioDTO.setLogin(login);
		usuarioDTO.setPermissao(permissao);
		return usuarioDTO;
	}

	public List<UsuarioDTO> buidList() {

		List<UsuarioDTO> usuarios = new ArrayList<>();
		for (Integer i = 0; i < quantidadeItens; i++) {
			UsuarioDTO usuarioDTO = new UsuarioDTO();
			usuarioDTO.setLogin("Usuario ".concat(i.toString()));
			usuarioDTO.setPermissao("Permissao ".concat(i.toString()));
			usuarios.add(usuarioDTO);
		}

		return usuarios;
	}

}
