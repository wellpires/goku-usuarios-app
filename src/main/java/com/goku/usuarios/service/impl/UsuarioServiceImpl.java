package com.goku.usuarios.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

import com.goku.usuarios.builder.UsuarioBuilder;
import com.goku.usuarios.dto.DetalheUsuarioDTO;
import com.goku.usuarios.dto.EditarUsuarioDTO;
import com.goku.usuarios.dto.NovoUsuarioDTO;
import com.goku.usuarios.dto.NovoUsuarioMasterDTO;
import com.goku.usuarios.dto.UsuarioDTO;
import com.goku.usuarios.enums.Permissao;
import com.goku.usuarios.exception.UsuarioDuplicadoException;
import com.goku.usuarios.exception.UsuarioMasterExistenteException;
import com.goku.usuarios.exception.UsuarioNotFoundException;
import com.goku.usuarios.function.Usuario2DetalheUsuarioDTOFunction;
import com.goku.usuarios.function.Usuario2UsuarioDTOFunction;
import com.goku.usuarios.model.Usuario;
import com.goku.usuarios.repository.UsuarioRepository;
import com.goku.usuarios.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	@CacheEvict(cacheNames = { "listar-usuarios-cache", "usuario-cache" }, allEntries = true)
	public void criarUsuario(NovoUsuarioDTO novoUsuarioDTO) {

		if (usuarioRepository.findById(novoUsuarioDTO.getLogin()).isPresent()) {
			throw new UsuarioDuplicadoException();
		}

		Usuario usuario = new UsuarioBuilder().login(novoUsuarioDTO.getLogin()).senha(novoUsuarioDTO.getSenha())
				.permissao(Permissao.COMUM.name()).build();
		usuarioRepository.save(usuario);

	}

	@Override
	public List<UsuarioDTO> listarUsuarios() {
		return usuarioRepository.findAll().stream().map(new Usuario2UsuarioDTOFunction()).collect(Collectors.toList());
	}

	@Override
	@CacheEvict(cacheNames = { "listar-usuarios-cache" }, allEntries = true)
	@CachePut(cacheNames = "usuario-cache")
	public void deletarUsuario(String login) {
		Usuario usuario = usuarioRepository.findById(login).orElseThrow(UsuarioNotFoundException::new);
		usuarioRepository.delete(usuario);
	}

	@Override
	@CacheEvict(cacheNames = { "listar-usuarios-cache" }, allEntries = true)
	@CachePut(cacheNames = "usuario-cache")
	public void editarUsuario(String login, EditarUsuarioDTO editarUsuarioDTO) {
		Usuario usuario = usuarioRepository.findById(login).orElseThrow(UsuarioNotFoundException::new);
		Usuario usuarioChanged = new UsuarioBuilder().source(editarUsuarioDTO).target(usuario).modify();

		usuarioRepository.save(usuarioChanged);
	}

	@Override
	public DetalheUsuarioDTO buscarUsuario(String login) {
		return usuarioRepository.findById(login).map(new Usuario2DetalheUsuarioDTOFunction())
				.orElseThrow(UsuarioNotFoundException::new);
	}

	@Override
	public void criarUsuarioMaster(NovoUsuarioMasterDTO novoUsuarioMasterDTO) {

		if (usuarioRepository.findByPermissao(Permissao.MASTER.name()).isPresent()) {
			throw new UsuarioMasterExistenteException();
		}

		Usuario usuario = new UsuarioBuilder().login(novoUsuarioMasterDTO.getLogin())
				.senha(novoUsuarioMasterDTO.getSenha()).permissao(Permissao.MASTER.name()).build();
		usuarioRepository.save(usuario);

	}

}
