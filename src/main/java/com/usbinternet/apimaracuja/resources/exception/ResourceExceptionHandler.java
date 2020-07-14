package com.usbinternet.apimaracuja.resources.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.usbinternet.apimaracuja.services.exceptions.AuthorizationException;
import com.usbinternet.apimaracuja.services.exceptions.DataIntegrityException;
import com.usbinternet.apimaracuja.services.exceptions.ObjectNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StanderError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request) {
		StanderError se = new StanderError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(), "Não encontrado",
				e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(se);

	}

	@ExceptionHandler(DataIntegrityException.class)
	public ResponseEntity<StanderError> dataIntegrity(DataIntegrityException e, HttpServletRequest request) {
		StanderError se = new StanderError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(),
				"Integridade de dados", e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(se);

	}

	@ExceptionHandler(AuthorizationException.class)
	public ResponseEntity<StanderError> authorization(AuthorizationException e, HttpServletRequest request) {

		StanderError err = new StanderError(System.currentTimeMillis(), HttpStatus.FORBIDDEN.value(), "Acesso negado",
				e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(err);
	}

}
