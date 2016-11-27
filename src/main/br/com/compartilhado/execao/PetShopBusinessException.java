package br.com.compartilhado.execao;

public class PetShopBusinessException extends Exception {
	private static final long serialVersionUID = 1L;

	public PetShopBusinessException() {
	}

	public PetShopBusinessException(String msg) {
		super(msg);
	}

	public PetShopBusinessException(String message, Throwable cause) {
		super(message, cause);
	}

	public PetShopBusinessException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public PetShopBusinessException(Throwable cause) {
		super(cause);
	}
}
