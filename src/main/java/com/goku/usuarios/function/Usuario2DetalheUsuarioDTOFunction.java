package com.goku.usuarios.function;

import java.util.function.Function;

import com.goku.usuarios.builder.DetalheUsuarioDTOBuilder;
import com.goku.usuarios.dto.DetalheUsuarioDTO;
import com.goku.usuarios.model.Usuario;

public class Usuario2DetalheUsuarioDTOFunction implements Function<Usuario, DetalheUsuarioDTO> {

	@Override
	public DetalheUsuarioDTO apply(Usuario usuario) {
		return new DetalheUsuarioDTOBuilder().login(usuario.getLogin()).senha(usuario.getSenha()).build();
	}

}
