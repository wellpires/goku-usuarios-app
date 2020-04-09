package com.goku.usuarios.exception;

public class UsuarioNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 2350750377511305762L;

	public UsuarioNotFoundException() {
		super("Usuário não encontrado!");
	}

}
