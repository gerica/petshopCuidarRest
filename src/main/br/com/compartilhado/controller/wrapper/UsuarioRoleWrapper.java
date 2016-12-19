package br.com.compartilhado.controller.wrapper;

import java.util.List;

import br.com.compartilhado.entidade.Usuario;
import br.com.compartilhado.entidade.permissao.Role;

public class UsuarioRoleWrapper {

	private Usuario usuario;
	private List<Role> roles;

	public List<Role> getRoles() {
		return roles;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
