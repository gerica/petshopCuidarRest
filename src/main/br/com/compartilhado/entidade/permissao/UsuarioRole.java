package br.com.compartilhado.entidade.permissao;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.compartilhado.entidade.Usuario;

@Entity
@Table(name = "tb_usuario_role")
public class UsuarioRole implements Serializable, GrantedAuthority {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_usuario_role")
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JsonIgnore
	@JoinColumn(name = "id_usuario", nullable = false)
	private Usuario usuario;

	@ManyToOne(fetch = FetchType.EAGER)
	@JsonIgnore
	@JoinColumn(name = "id_role", nullable = false)
	private Role role;

	@Override
	public String getAuthority() {
		return role.getNome();
	}

	public Long getId() {
		return id;
	}

	public Role getRole() {
		return role;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
