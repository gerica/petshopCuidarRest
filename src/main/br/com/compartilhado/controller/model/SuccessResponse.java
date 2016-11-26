package br.com.compartilhado.controller.model;

public class SuccessResponse extends AbstractResponse {

	public SuccessResponse(String message) {
		super(message);
	}

	public SuccessResponse(String message, Object obj) {
		super(message, obj);
	}

}
