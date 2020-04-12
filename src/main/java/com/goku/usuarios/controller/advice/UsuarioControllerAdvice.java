package com.goku.usuarios.controller.advice;

import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.goku.usuarios.builder.ErrorResponseBuilder;
import com.goku.usuarios.exception.UsuarioDuplicadoException;
import com.goku.usuarios.exception.UsuarioMasterExistenteException;
import com.goku.usuarios.exception.UsuarioNotFoundException;
import com.goku.usuarios.response.ErrorResponse;

@RestControllerAdvice
public class UsuarioControllerAdvice {

	@Value("${message.error-generic}")
	private String genericMessage;

	private static final Logger log = LoggerFactory.getLogger(UsuarioControllerAdvice.class);

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleException(Exception exception) {

		log.error(exception.getMessage(), exception);

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(new ErrorResponseBuilder().message(genericMessage).build());

	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(
			MethodArgumentNotValidException methodArgumentNotValidException) {

		log.error(methodArgumentNotValidException.getMessage(), methodArgumentNotValidException);

		ObjectError message = methodArgumentNotValidException.getBindingResult().getGlobalError();
		if (Objects.nonNull(methodArgumentNotValidException.getBindingResult().getFieldError())) {
			message = methodArgumentNotValidException.getBindingResult().getFieldError();
		}

		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(new ErrorResponseBuilder().message(message.getDefaultMessage()).build());
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

	@ExceptionHandler(UsuarioMasterExistenteException.class)
	public ResponseEntity<ErrorResponse> handleUsuarioMasterExistenteException(
			UsuarioMasterExistenteException usuarioMasterExistenteException) {

		log.error(usuarioMasterExistenteException.getMessage(), usuarioMasterExistenteException);

		return ResponseEntity.status(HttpStatus.CONFLICT)
				.body(new ErrorResponseBuilder().message(usuarioMasterExistenteException.getMessage()).build());
	}

}
