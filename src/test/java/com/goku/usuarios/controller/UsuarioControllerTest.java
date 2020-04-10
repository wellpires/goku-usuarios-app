package com.goku.usuarios.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.goku.usuarios.builder.DetalheUsuarioDTOBuilder;
import com.goku.usuarios.builder.EditarUsuarioDTOBuilder;
import com.goku.usuarios.builder.NovoUsuarioDTOBuilder;
import com.goku.usuarios.controller.advice.UsuarioControllerAdvice;
import com.goku.usuarios.dto.DetalheUsuarioDTO;
import com.goku.usuarios.dto.EditarUsuarioDTO;
import com.goku.usuarios.dto.NovoUsuarioDTO;
import com.goku.usuarios.exception.UsuarioDuplicadoException;
import com.goku.usuarios.exception.UsuarioNotFoundException;
import com.goku.usuarios.response.DetalheUsuarioResponse;
import com.goku.usuarios.service.UsuarioService;

@SpringBootTest
class UsuarioControllerTest {

	private static final String PATH_APP = "/api/v1/usuarios";
	private static final String POST_CRIAR_USUARIO = PATH_APP;
	private static final String PUT_EDITAR_USUARIO = PATH_APP.concat("/{login}");
	private static final String DELETE_DELETAR_USUARIO = PATH_APP.concat("/{login}");
	private static final String GET_BUSCAR_USUARIO = PATH_APP.concat("/{login}");
	private static final String GET_LISTA_USUARIOS = PATH_APP;

	@InjectMocks
	private UsuarioController usuarioController;

	@Mock
	private UsuarioService usuarioService;

	private MockMvc mockMVC;

	private ObjectMapper mapper;

	@BeforeEach
	public void setup() {
		this.mockMVC = MockMvcBuilders.standaloneSetup(usuarioController)
				.setControllerAdvice(new UsuarioControllerAdvice()).build();

		this.mapper = new ObjectMapper();

	}

	@Test
	void deveCriarUsuario() throws Exception {

		NovoUsuarioDTO novoUsuarioDTOBuilt = new NovoUsuarioDTOBuilder().login("loginTeste123").senha("senhaTeste123")
				.build();

		mockMVC.perform(post(POST_CRIAR_USUARIO).contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(novoUsuarioDTOBuilt))).andDo(print())
				.andExpect(status().isNoContent());

		verify(usuarioService, times(1)).criarUsuario(any(NovoUsuarioDTO.class));

	}

	@Test
	void naoDeveCriarUsuarioPoisJaExiste() throws Exception {

		doThrow(UsuarioDuplicadoException.class).when(usuarioService).criarUsuario(any(NovoUsuarioDTO.class));

		NovoUsuarioDTO novoUsuarioDTOBuilt = new NovoUsuarioDTOBuilder().login("loginTeste123").senha("senhaTeste123")
				.build();

		mockMVC.perform(post(POST_CRIAR_USUARIO).contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(novoUsuarioDTOBuilt))).andDo(print())
				.andExpect(status().isConflict());

		verify(usuarioService, times(1)).criarUsuario(any(NovoUsuarioDTO.class));

	}

	@Test
	void naoDeveCriarUsuarioPoisOCampoLoginNaoFoiFornecido() throws JsonProcessingException, Exception {

		NovoUsuarioDTO novoUsuarioDTOBuilt = new NovoUsuarioDTOBuilder().senha("senhaTeste123").build();

		mockMVC.perform(post(POST_CRIAR_USUARIO).contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(novoUsuarioDTOBuilt))).andDo(print())
				.andExpect(status().isBadRequest());

		verify(usuarioService, never()).criarUsuario(any(NovoUsuarioDTO.class));

	}

	@Test
	void naoDeveCriarUsuarioPoisOCampoSenhaNaoFoiFornecido() throws JsonProcessingException, Exception {

		NovoUsuarioDTO novoUsuarioDTOBuilt = new NovoUsuarioDTOBuilder().login("loginTeste123").build();

		mockMVC.perform(post(POST_CRIAR_USUARIO).contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(novoUsuarioDTOBuilt))).andDo(print())
				.andExpect(status().isBadRequest());

		verify(usuarioService, never()).criarUsuario(any(NovoUsuarioDTO.class));

	}

	@Test
	void deveEditarUsuario() throws Exception {

		Map<String, Object> parameters = new HashMap<>();
		parameters.put("login", "usuarioTeste123");
		URI putEditarUsuario = UriComponentsBuilder.fromPath(PUT_EDITAR_USUARIO).buildAndExpand(parameters).toUri();

		EditarUsuarioDTO editarUsuarioDTO = new EditarUsuarioDTOBuilder().senha("senhaTeste123").build();

		mockMVC.perform(put(putEditarUsuario).contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(editarUsuarioDTO))).andDo(print()).andExpect(status().isNoContent());

		verify(usuarioService, times(1)).editarUsuario(anyString(), any(EditarUsuarioDTO.class));

	}

	@Test
	void naoDeveEditarUsuarioPoisOCampoSenhaNaoFoiFornecido() throws JsonProcessingException, Exception {

		Map<String, Object> parameters = new HashMap<>();
		parameters.put("login", "usuarioTeste123");
		URI putEditarUsuario = UriComponentsBuilder.fromPath(PUT_EDITAR_USUARIO).buildAndExpand(parameters).toUri();

		EditarUsuarioDTO editarUsuarioDTO = new EditarUsuarioDTOBuilder().build();

		mockMVC.perform(put(putEditarUsuario).contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(editarUsuarioDTO))).andDo(print())
				.andExpect(status().isBadRequest());

		verify(usuarioService, never()).editarUsuario(anyString(), any(EditarUsuarioDTO.class));

	}

	@Test
	void naoDeveEditarUsuarioPoisOUsuarioNaoExiste() throws JsonProcessingException, Exception {

		doThrow(UsuarioNotFoundException.class).when(usuarioService).editarUsuario(anyString(),
				any(EditarUsuarioDTO.class));

		Map<String, Object> parameters = new HashMap<>();
		parameters.put("login", "usuarioTeste123");
		URI putEditarUsuario = UriComponentsBuilder.fromPath(PUT_EDITAR_USUARIO).buildAndExpand(parameters).toUri();

		EditarUsuarioDTO editarUsuarioDTO = new EditarUsuarioDTOBuilder().senha("senhaTeste123").build();

		mockMVC.perform(put(putEditarUsuario).contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(editarUsuarioDTO))).andDo(print()).andExpect(status().isNotFound());

		verify(usuarioService, times(1)).editarUsuario(anyString(), any(EditarUsuarioDTO.class));

	}

	@Test
	void deveDeletarUsuario() throws Exception {

		Map<String, Object> parameters = new HashMap<>();
		parameters.put("login", "usuarioTeste123");
		URI deleteDeletarUsuario = UriComponentsBuilder.fromPath(DELETE_DELETAR_USUARIO).buildAndExpand(parameters)
				.toUri();

		mockMVC.perform(delete(deleteDeletarUsuario)).andDo(print()).andExpect(status().isNoContent());

		verify(usuarioService, times(1)).deletarUsuario(anyString());

	}

	@Test
	void naoDeveDeletarUsuarioPoisUsuarioNaoExiste() throws Exception {

		doThrow(UsuarioNotFoundException.class).when(usuarioService).deletarUsuario(anyString());

		Map<String, Object> parameters = new HashMap<>();
		parameters.put("login", "usuarioTeste123");
		URI deleteDeletarUsuario = UriComponentsBuilder.fromPath(DELETE_DELETAR_USUARIO).buildAndExpand(parameters)
				.toUri();

		mockMVC.perform(delete(deleteDeletarUsuario)).andDo(print()).andExpect(status().isNotFound());

		verify(usuarioService, times(1)).deletarUsuario(anyString());

	}

	@Test
	void deveBuscarUsuario() throws Exception {

		DetalheUsuarioDTO detalheUsuario = new DetalheUsuarioDTOBuilder().login("usuarioTeste123")
				.senha("senhaTeste123").build();
		when(usuarioService.buscarUsuario(anyString())).thenReturn(detalheUsuario);

		Map<String, Object> parameters = new HashMap<>();
		parameters.put("login", "usuarioTeste123");
		URI getBuscarUsuario = UriComponentsBuilder.fromPath(GET_BUSCAR_USUARIO).buildAndExpand(parameters).toUri();

		MvcResult response = mockMVC.perform(get(getBuscarUsuario)).andDo(print()).andExpect(status().isOk())
				.andReturn();

		DetalheUsuarioResponse detalheUsuarioResponse = mapper.readValue(response.getResponse().getContentAsString(),
				DetalheUsuarioResponse.class);

		assertThat(detalheUsuarioResponse.getDetalheUsuarioDTO()).isNotNull();
		assertThat(detalheUsuarioResponse.getDetalheUsuarioDTO().getLogin()).isEqualTo(detalheUsuario.getLogin());
		assertThat(detalheUsuarioResponse.getDetalheUsuarioDTO().getSenha()).isEqualTo(detalheUsuario.getSenha());

	}

	@Test
	void naoDeveBuscarUsuarioPoisUsuarioNaoExiste() throws Exception {

		doThrow(UsuarioNotFoundException.class).when(usuarioService).buscarUsuario(anyString());

		Map<String, Object> parameters = new HashMap<>();
		parameters.put("login", "usuarioTeste123");
		URI getBuscarUsuario = UriComponentsBuilder.fromPath(GET_BUSCAR_USUARIO).buildAndExpand(parameters).toUri();

		mockMVC.perform(get(getBuscarUsuario)).andDo(print()).andExpect(status().isNotFound());

	}

	@Test
	void deveListarUsuarios() throws Exception {

		mockMVC.perform(get(GET_LISTA_USUARIOS)).andDo(print()).andExpect(status().isOk());

		verify(usuarioService, times(1)).listarUsuarios();

	}

	@Test
	void deveRetornarInternalServerError() throws Exception {

		doThrow(RuntimeException.class).when(usuarioService).buscarUsuario(anyString());

		Map<String, Object> parameters = new HashMap<>();
		parameters.put("login", "usuarioTeste123");
		URI getBuscarUsuario = UriComponentsBuilder.fromPath(GET_BUSCAR_USUARIO).buildAndExpand(parameters).toUri();

		mockMVC.perform(get(getBuscarUsuario)).andDo(print()).andExpect(status().isInternalServerError());

	}

}
