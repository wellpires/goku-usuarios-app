package com.goku.usuarios.controller.advice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.goku.usuarios.builder.ErrorResponseBuilder;
import com.goku.usuarios.exception.UsuarioDuplicadoException;
import com.goku.usuarios.exception.UsuarioNotFoundException;
import com.goku.usuarios.response.ErrorResponse;

@RestControllerAdvice
public class UsuarioControllerAdvice {

	private static final Logger log = LoggerFactory.getLogger(UsuarioControllerAdvice.class);

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleException(Exception exception) {

		log.error(exception.getMessage(), exception);

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(new ErrorResponseBuilder().message(exception.getMessage()).build());

	}

	@ExceptionHandler(UsuarioNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleUsuarioNotFoundException(
			UsuarioNotFoundException usuarioNotFoundException) {

		log.error(usuarioNotFoundException.getMessage(), usuarioNotFoundException);

		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(new ErrorResponseBuilder().message(usuarioNotFoundException.getMessage()).build());
	}

	@ExceptionHandler(UsuarioDuplicadoException.class)
	public ResponseEntity<ErrorResponse> handleUsuarioDuplicadoException(
			UsuarioDuplicadoException usuarioDuplicadoException) {

		log.error(usuarioDuplicadoException.getMessage(), usuarioDuplicadoException);

		return ResponseEntity.status(HttpStatus.CONFLICT)
				.body(new ErrorResponseBuilder().message(usuarioDuplicadoException.getMessage()).build());
	}

}
