package com.goku.usuarios.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.TestPropertySource;

import com.goku.usuarios.builder.EditarUsuarioDTOBuilder;
import com.goku.usuarios.builder.NovoUsuarioDTOBuilder;
import com.goku.usuarios.builder.NovoUsuarioMasterDTOBuilder;
import com.goku.usuarios.builder.UsuarioBuilder;
import com.goku.usuarios.dto.DetalheUsuarioDTO;
import com.goku.usuarios.dto.NovoUsuarioDTO;
import com.goku.usuarios.dto.NovoUsuarioMasterDTO;
import com.goku.usuarios.dto.UsuarioDTO;
import com.goku.usuarios.enums.Permissao;
import com.goku.usuarios.exception.UsuarioDuplicadoException;
import com.goku.usuarios.exception.UsuarioMasterExistenteException;
import com.goku.usuarios.exception.UsuarioNotFoundException;
import com.goku.usuarios.model.Usuario;
import com.goku.usuarios.repository.UsuarioRepository;

@SpringBootTest
@TestPropertySource(properties = "message.error-generic = Erro interno!")
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

		Usuario usuarioBuilt = new UsuarioBuilder().login("usuarioTeste").senha("senhaTeste").build();
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

		Usuario usuarioBuilt = new UsuarioBuilder().login("usuarioTeste").senha("senhaTeste").build();
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

		Usuario usuarioBuilt = new UsuarioBuilder().login("usuarioTeste").senha("senhaTeste").build();
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
		verify(usuarioRepository, times(1)).findById(anyString());

	}

	@Test
	void naoDeveBuscarUsuarioPoisUsuarioNaoExiste() {

		Usuario usuarioBuilt = null;
		when(usuarioRepository.findById(anyString())).thenReturn(Optional.ofNullable(usuarioBuilt));

		assertThrows(UsuarioNotFoundException.class, () -> usuarioService.buscarUsuario("usuario123"));

		verify(usuarioRepository, times(1)).findById(anyString());

	}

	@Test
	void deveCriarUsuarioMaster() {

		Usuario usuarioBuilt = null;
		when(usuarioRepository.findByPermissao(anyString())).thenReturn(Optional.ofNullable(usuarioBuilt));

		NovoUsuarioMasterDTO novoUsuarioMasterBuilt = new NovoUsuarioMasterDTOBuilder().login("loginTeste")
				.senha("senhaTeste").build();
		usuarioService.criarUsuarioMaster(novoUsuarioMasterBuilt);

		ArgumentCaptor<Usuario> argCaptor = ArgumentCaptor.forClass(Usuario.class);
		verify(usuarioRepository, times(1)).save(argCaptor.capture());
		assertNotNull(argCaptor.getValue());
		assertThat(argCaptor.getValue().getLogin()).isEqualTo(novoUsuarioMasterBuilt.getLogin());
		assertTrue(new BCryptPasswordEncoder().matches(novoUsuarioMasterBuilt.getSenha(),
				argCaptor.getValue().getSenha()));

	}

	@Test
	void naoDeveCriarUsuarioMasterPoisJaFoiCriado() {

		Usuario usuarioBuilt = new UsuarioBuilder().login("loginTesteEcontrado").senha("senhaTesteEncontrado")
				.permissao(Permissao.COMUM.name()).build();
		when(usuarioRepository.findByPermissao(anyString())).thenReturn(Optional.ofNullable(usuarioBuilt));

		NovoUsuarioMasterDTO novoUsuarioMasterBuilt = new NovoUsuarioMasterDTOBuilder().login("loginTeste")
				.senha("senhaTeste").build();

		assertThrows(UsuarioMasterExistenteException.class,
				() -> usuarioService.criarUsuarioMaster(novoUsuarioMasterBuilt));

		verify(usuarioRepository, never()).save(any(Usuario.class));

	}

}
