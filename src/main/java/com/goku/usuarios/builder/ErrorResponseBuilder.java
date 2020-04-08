package com.goku.usuarios.builder;

import com.goku.usuarios.response.ErrorResponse;

public class ErrorResponseBuilder {

	private String message;

	public ErrorResponseBuilder message(String message) {
		this.message = message;
		return this;
	}

	public ErrorResponse build() {
		ErrorResponse errorReponse = new ErrorResponse();
		errorReponse.setMessage(message);
		return errorReponse;
	}

}
