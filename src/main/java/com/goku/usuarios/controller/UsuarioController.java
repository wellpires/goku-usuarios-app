package com.goku.usuarios.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.goku.usuarios.controller.resource.UsuarioResource;
import com.goku.usuarios.dto.EditarUsuarioDTO;
import com.goku.usuarios.dto.NovoUsuarioDTO;
import com.goku.usuarios.response.DetalheUsuarioResponse;
import com.goku.usuarios.response.UsuariosResponse;
import com.goku.usuarios.service.UsuarioService;

@RestController
@RequestMapping("api/v1/usuarios")
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
	public ResponseEntity<Void> editarUsuario(Long id, EditarUsuarioDTO editarUsuarioDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Void> deletarUsuario(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<DetalheUsuarioResponse> buscarUsuario(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<UsuariosResponse> listarUsuarios() {
		// TODO Auto-generated method stub
		return null;
	}

}
