package com.goku.usuarios.controller.resource;

import org.springframework.http.ResponseEntity;

import com.goku.usuarios.dto.EditarUsuarioDTO;
import com.goku.usuarios.dto.NovoUsuarioDTO;
import com.goku.usuarios.response.DetalheUsuarioResponse;
import com.goku.usuarios.response.UsuariosResponse;

public interface UsuarioResource {

	public ResponseEntity<Void> criarUsuario(NovoUsuarioDTO novoUsuarioDTO);

	public ResponseEntity<Void> editarUsuario(Long id, EditarUsuarioDTO editarUsuarioDTO);

	public ResponseEntity<Void> deletarUsuario(Long id);

	public ResponseEntity<DetalheUsuarioResponse> buscarUsuario(Long id);

	public ResponseEntity<UsuariosResponse> listarUsuarios();

}
