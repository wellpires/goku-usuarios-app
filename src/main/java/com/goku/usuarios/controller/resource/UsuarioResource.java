package com.goku.usuarios.controller.resource;

import org.springframework.http.ResponseEntity;

import com.goku.usuarios.dto.EditarUsuarioDTO;
import com.goku.usuarios.dto.NovoUsuarioDTO;
import com.goku.usuarios.response.DetalheUsuarioResponse;
import com.goku.usuarios.response.UsuariosResponse;

public interface UsuarioResource {

	public ResponseEntity<Void> criarUsuario(NovoUsuarioDTO novoUsuarioDTO);

	public ResponseEntity<Void> editarUsuario(String login, EditarUsuarioDTO editarUsuarioDTO);

	public ResponseEntity<Void> deletarUsuario(String login);

	public ResponseEntity<DetalheUsuarioResponse> buscarUsuario(String login);

	public ResponseEntity<UsuariosResponse> listarUsuarios();

}
