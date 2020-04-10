package com.goku.usuarios.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.goku.usuarios.controller.resource.UsuarioResource;
import com.goku.usuarios.dto.DetalheUsuarioDTO;
import com.goku.usuarios.dto.EditarUsuarioDTO;
import com.goku.usuarios.dto.NovoUsuarioDTO;
import com.goku.usuarios.dto.UsuarioDTO;
import com.goku.usuarios.response.DetalheUsuarioResponse;
import com.goku.usuarios.response.UsuariosResponse;
import com.goku.usuarios.service.UsuarioService;

@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioController implements UsuarioResource {

	@Autowired
	private UsuarioService usuarioService;

	@Override
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> criarUsuario(@Valid @RequestBody NovoUsuarioDTO novoUsuarioDTO) {
		usuarioService.criarUsuario(novoUsuarioDTO);
		return ResponseEntity.noContent().build();
	}

	@Override
	@PutMapping(path = "/{login}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> editarUsuario(@PathVariable("login") String login,
			@Valid @RequestBody EditarUsuarioDTO editarUsuarioDTO) {
		usuarioService.editarUsuario(login, editarUsuarioDTO);
		return ResponseEntity.noContent().build();
	}

	@Override
	@DeleteMapping(path = "/{login}")
	public ResponseEntity<Void> deletarUsuario(@PathVariable("login") String login) {
		usuarioService.deletarUsuario(login);
		return ResponseEntity.noContent().build();
	}

	@Override
	@GetMapping(path = "/{login}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DetalheUsuarioResponse> buscarUsuario(@PathVariable("login") String login) {
		DetalheUsuarioDTO detalheUsuarioDTO = usuarioService.buscarUsuario(login);
		return ResponseEntity.ok(new DetalheUsuarioResponse(detalheUsuarioDTO));
	}

	@Override
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UsuariosResponse> listarUsuarios() {
		List<UsuarioDTO> usuarios = usuarioService.listarUsuarios();
		return ResponseEntity.ok(new UsuariosResponse(usuarios));
	}

}
