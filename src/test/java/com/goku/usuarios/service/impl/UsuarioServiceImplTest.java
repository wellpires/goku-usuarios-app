package com.goku.usuarios.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.goku.usuarios.builder.EditarUsuarioDTOBuilder;
import com.goku.usuarios.builder.NovoUsuarioDTOBuilder;
import com.goku.usuarios.builder.UsuarioBuilder;
import com.goku.usuarios.dto.DetalheUsuarioDTO;
import com.goku.usuarios.dto.NovoUsuarioDTO;
import com.goku.usuarios.dto.UsuarioDTO;
import com.goku.usuarios.exception.UsuarioDuplicadoException;
import com.goku.usuarios.exception.UsuarioNotFoundException;
import com.goku.usuarios.model.Usuario;
import com.goku.usuarios.repository.UsuarioRepository;

@SpringBootTest
class UsuarioServiceImplTest {

	@Mock
	private UsuarioRepository usuarioRepository;

	@InjectMocks
	private UsuarioServiceImpl usuarioService;

	@Test
	void deveCriarUsuario() {

		Usuario usuarioBuilt = null;
		when(usuarioRepository.findById(anyString())).thenReturn(Optional.ofNullable(usuarioBuilt));

		NovoUsuarioDTO novoUsuarioDTO = new NovoUsuarioDTOBuilder().login("usuario123").senha("senha123").build();
		usuarioService.criarUsuario(novoUsuarioDTO);

		verify(usuarioRepository, times(1)).save(any());

	}

	@Test
	void naoDeveCriarUsuarioPoisJaExiste() {

		Usuario usuarioBuilt = new UsuarioBuilder().build();
		when(usuarioRepository.findById(anyString())).thenReturn(Optional.ofNullable(usuarioBuilt));

		NovoUsuarioDTO novoUsuarioDTO = new NovoUsuarioDTOBuilder().login("usuario123").senha("senha123").build();

		assertThrows(UsuarioDuplicadoException.class, () -> usuarioService.criarUsuario(novoUsuarioDTO));

		verify(usuarioRepository, never()).save(any());

	}

	@Test
	void deveListarUsuarios() {

		when(usuarioRepository.findAll()).thenReturn(new UsuarioBuilder().quantidadeItens(50).buildList());

		List<UsuarioDTO> usuarios = usuarioService.listarUsuarios();

		assertThat(usuarios).hasSize(50);

	}

	@Test
	void deveDeletarUsuario() {

		Usuario usuarioBuilt = new UsuarioBuilder().build();
		when(usuarioRepository.findById(anyString())).thenReturn(Optional.ofNullable(usuarioBuilt));

		usuarioService.deletarUsuario("loginTeste");

		verify(usuarioRepository, times(1)).delete(any(Usuario.class));

	}

	@Test
	void naoDeveDeletarUsuarioPoisNaoFoiEncontrado() {

		Usuario usuarioBuilt = null;
		when(usuarioRepository.findById(anyString())).thenReturn(Optional.ofNullable(usuarioBuilt));

		assertThrows(UsuarioNotFoundException.class, () -> usuarioService.deletarUsuario("loginTeste"));

		verify(usuarioRepository, never()).delete(any(Usuario.class));

	}

	@Test
	void deveEditarUsuario() {

		Usuario usuarioBuilt = new UsuarioBuilder().build();
		when(usuarioRepository.findById(anyString())).thenReturn(Optional.ofNullable(usuarioBuilt));

		usuarioService.editarUsuario("usuario123", new EditarUsuarioDTOBuilder().senha("senha123").build());

		verify(usuarioRepository, times(1)).save(any(Usuario.class));

	}

	@Test
	void naoDeveEditarUsuarioPoisNaoExiste() {

		Usuario usuarioBuilt = null;
		when(usuarioRepository.findById(anyString())).thenReturn(Optional.ofNullable(usuarioBuilt));

		assertThrows(UsuarioNotFoundException.class, () -> usuarioService.editarUsuario("usuario123",
				new EditarUsuarioDTOBuilder().senha("senha123").build()));

		verify(usuarioRepository, never()).save(any(Usuario.class));

	}

	@Test
	void deveBuscarUsuario() {

		Usuario usuarioBuilt = new UsuarioBuilder().login("loginTeste123").senha("senhaTeste123").build();
		when(usuarioRepository.findById(anyString())).thenReturn(Optional.ofNullable(usuarioBuilt));

		DetalheUsuarioDTO usuarioFound = usuarioService.buscarUsuario("usuario123");

		assertThat(usuarioFound.getLogin()).isEqualTo("loginTeste123");
		assertThat(usuarioFound.getSenha()).isEqualTo("senhaTeste123");

	}

	@Test
	void naoDeveBuscarUsuarioPoisUsuarioNaoExiste() {

	}

}
