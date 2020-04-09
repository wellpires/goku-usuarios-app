package com.goku.usuarios.function;

import java.util.function.Function;

import com.goku.usuarios.builder.UsuarioDTOBuilder;
import com.goku.usuarios.dto.UsuarioDTO;
import com.goku.usuarios.model.Usuario;

public class Usuario2UsuarioDTOFunction implements Function<Usuario, UsuarioDTO> {

	@Override
	public UsuarioDTO apply(Usuario usuario) {
		return new UsuarioDTOBuilder().login(usuario.getLogin()).build();
	}

}
