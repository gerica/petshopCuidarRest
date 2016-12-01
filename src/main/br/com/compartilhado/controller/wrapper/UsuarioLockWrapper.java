package br.com.compartilhado.controller.wrapper;

public class UsuarioLockWrapper {
	private boolean lock;
	private String usuarioEmail;

	public UsuarioLockWrapper(boolean lock, String usuarioEmail) {
		super();
		this.lock = lock;
		this.usuarioEmail = usuarioEmail;
	}

	public String getUsuarioEmail() {
		return usuarioEmail;
	}

	public boolean isLock() {
		return lock;
	}

	public void setLock(boolean lock) {
		this.lock = lock;
	}

	public void setUsuarioEmail(String usuarioEmail) {
		this.usuarioEmail = usuarioEmail;
	};

}
