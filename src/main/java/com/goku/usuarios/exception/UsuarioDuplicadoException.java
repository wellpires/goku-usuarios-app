package com.goku.usuarios.exception;

public class UsuarioDuplicadoException extends RuntimeException {

	private static final long serialVersionUID = 8134906271050045949L;

	public UsuarioDuplicadoException() {
		super("Usuário já existente!");
	}

}
