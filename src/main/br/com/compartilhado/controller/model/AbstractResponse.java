package br.com.compartilhado.controller.model;

public abstract class AbstractResponse {
	private String message;
	private Object objeto;

	public AbstractResponse() {
		super();
	}

	public AbstractResponse(String message) {
		this.message = message;
	}

	public AbstractResponse(String message, Object obj) {
		this.message = message;
		this.objeto = obj;
	}

	public String getMessage() {
		return message;
	}

	public Object getObjeto() {
		return objeto;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setObjeto(Object objeto) {
		this.objeto = objeto;
	}

}
