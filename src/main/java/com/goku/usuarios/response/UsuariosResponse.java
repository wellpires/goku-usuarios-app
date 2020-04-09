package com.goku.usuarios.response;

import java.util.List;

import com.goku.usuarios.dto.UsuarioDTO;

public class UsuariosResponse {

	private List<UsuarioDTO> usuarios;

	public UsuariosResponse(List<UsuarioDTO> usuarios) {
		this.usuarios = usuarios;
	}

	public List<UsuarioDTO> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<UsuarioDTO> usuarios) {
		this.usuarios = usuarios;
	}

}
