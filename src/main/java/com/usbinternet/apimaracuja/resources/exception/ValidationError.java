package com.usbinternet.apimaracuja.resources.exception;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StanderError {

	private static final long serialVersionUID = 1L;

	private List<FieldMessage> errors = new ArrayList<>();

	public ValidationError(Long timeStamp, Integer status, String error, String msg, String path) {
		super(timeStamp, status, error, msg, path);
	}

	public List<FieldMessage> getErrors() {
		return errors;
	}

	public void addError(String fieldName, String message) {
		errors.add(new FieldMessage(fieldName, message));
	}

}
