package com.goku.usuarios.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goku.usuarios.builder.UsuarioBuilder;
import com.goku.usuarios.dto.NovoUsuarioDTO;
import com.goku.usuarios.exception.UsuarioDuplicadoException;
import com.goku.usuarios.model.Usuario;
import com.goku.usuarios.repository.UsuarioRepository;
import com.goku.usuarios.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public void criarUsuario(NovoUsuarioDTO novoUsuarioDTO) {

		if (usuarioRepository.findByLogin(novoUsuarioDTO.getLogin()).isPresent()) {
			throw new UsuarioDuplicadoException();
		}

		Usuario usuario = new UsuarioBuilder().login(novoUsuarioDTO.getLogin()).senha(novoUsuarioDTO.getSenha())
				.build();
		usuarioRepository.save(usuario);

	}

}
