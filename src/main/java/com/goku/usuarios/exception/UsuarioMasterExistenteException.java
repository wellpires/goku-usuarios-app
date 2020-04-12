package com.goku.usuarios.exception;

public class UsuarioMasterExistenteException extends RuntimeException {

	private static final long serialVersionUID = 3418336448419346812L;

	public UsuarioMasterExistenteException() {
		super("Usuário Master já existente!");
	}

}
