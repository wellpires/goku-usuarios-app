package com.goku.usuarios.service;

import java.util.List;

import com.goku.usuarios.dto.DetalheUsuarioDTO;
import com.goku.usuarios.dto.EditarUsuarioDTO;
import com.goku.usuarios.dto.NovoUsuarioDTO;
import com.goku.usuarios.dto.NovoUsuarioMasterDTO;
import com.goku.usuarios.dto.UsuarioDTO;

public interface UsuarioService {

	void criarUsuario(NovoUsuarioDTO novoUsuarioDTO);

	List<UsuarioDTO> listarUsuarios();

	void deletarUsuario(String login);

	void editarUsuario(String login, EditarUsuarioDTO editarUsuarioDTO);

	DetalheUsuarioDTO buscarUsuario(String login);

	void criarUsuarioMaster(NovoUsuarioMasterDTO novoUsuarioMasterDTO);

}
