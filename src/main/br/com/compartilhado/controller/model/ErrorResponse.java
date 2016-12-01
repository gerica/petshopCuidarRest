package br.com.compartilhado.controller.model;

public class ErrorResponse extends AbstractResponse {

	public ErrorResponse(String message) {
		super(message);
	}

	public ErrorResponse(String message, Object obj) {
		super(message, obj);
	}

}
