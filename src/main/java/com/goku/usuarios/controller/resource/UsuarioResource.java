package com.goku.usuarios.controller.resource;

import org.springframework.http.ResponseEntity;

import com.goku.usuarios.dto.EditarUsuarioDTO;
import com.goku.usuarios.dto.NovoUsuarioDTO;
import com.goku.usuarios.dto.NovoUsuarioMasterDTO;
import com.goku.usuarios.response.DetalheUsuarioResponse;
import com.goku.usuarios.response.ErrorResponse;
import com.goku.usuarios.response.UsuariosResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "Serviço que gerencia os usuários")
public interface UsuarioResource {

	@ApiOperation(value = "Cria um novo usuário")
	@ApiResponses(value = { @ApiResponse(code = 204, message = "No Content"),
			@ApiResponse(code = 400, message = "Bad Request", response = ErrorResponse.class),
			@ApiResponse(code = 409, message = "Conflict", response = ErrorResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = ErrorResponse.class) })
	public ResponseEntity<Void> criarUsuario(NovoUsuarioDTO novoUsuarioDTO);

	@ApiOperation(value = "Edita um usuário")
	@ApiResponses(value = { @ApiResponse(code = 204, message = "No Content"),
			@ApiResponse(code = 400, message = "Bad Request", response = ErrorResponse.class),
			@ApiResponse(code = 404, message = "Not Found", response = ErrorResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = ErrorResponse.class) })
	public ResponseEntity<Void> editarUsuario(String login, EditarUsuarioDTO editarUsuarioDTO);

	@ApiOperation(value = "Deleta um usuário")
	@ApiResponses(value = { @ApiResponse(code = 204, message = "No Content"),
			@ApiResponse(code = 404, message = "Not Found", response = ErrorResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = ErrorResponse.class) })
	public ResponseEntity<Void> deletarUsuario(String login);

	@ApiOperation(value = "Busca um usuário pelo Login")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 404, message = "Not Found", response = ErrorResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = ErrorResponse.class) })
	public ResponseEntity<DetalheUsuarioResponse> buscarUsuario(String login);

	@ApiOperation(value = "Lista os usuários")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 500, message = "Internal Server Error", response = ErrorResponse.class) })
	public ResponseEntity<UsuariosResponse> listarUsuarios();

	@ApiOperation(value = "Cria usuário master")
	@ApiResponses(value = { @ApiResponse(code = 204, message = "No Content"),
			@ApiResponse(code = 400, message = "Bad Request", response = ErrorResponse.class),
			@ApiResponse(code = 409, message = "Conflict", response = ErrorResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = ErrorResponse.class) })
	public ResponseEntity<Void> criarUsuarioMaster(NovoUsuarioMasterDTO novoUsuarioMasterDTO);
	
}
