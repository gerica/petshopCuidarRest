package br.com.compartilhado.entidade;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.compartilhado.entidade.permissao.UsuarioRole;

@Entity
@Table(name = "tb_usuario")
public class Usuario implements Serializable, UserDetails {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_usuario")
	private Long id;

	@Column(name = "ds_nome")
	private String username;

	@Column(name = "ds_email")
	private String email;

	@Column(name = "ds_senha")
	private String password;

	@Column(name = "in_ativo")
	private Boolean ativo;

	@Transient
	private Date lastPasswordReset;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "usuario", cascade = { CascadeType.ALL })
	// @JsonIgnore
	private Collection<UsuarioRole> authorities;

	@Transient
	private Boolean accountNonExpired = true;

	@Transient
	private Boolean accountNonLocked = true;
	@Transient
	private Boolean credentialsNonExpired = true;
	@Transient
	private Boolean enabled = true;

	public Usuario() {

	}

	@JsonIgnore
	public Boolean getAccountNonExpired() {
		return this.accountNonExpired;
	}

	@JsonIgnore
	public Boolean getAccountNonLocked() {
		return this.accountNonLocked;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	// @Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authorities;
	}

	@JsonIgnore
	public Boolean getCredentialsNonExpired() {
		return this.credentialsNonExpired;
	}

	public String getEmail() {
		return email;
	}

	@JsonIgnore
	public Boolean getEnabled() {
		return this.enabled;
	}

	// public String getAuthoritiesBd() {
	// return authoritiesBd;
	// }
	//
	// public void setAuthoritiesBd(String authoritiesBd) {
	// this.authoritiesBd = authoritiesBd;
	// }

	public Long getId() {
		return id;
	}

	public Date getLastPasswordReset() {
		return lastPasswordReset;
	}

	public String getPassword() {
		return password;
	}

	public String getUsername() {
		return username;
	}

	// @Override
	public boolean isAccountNonExpired() {
		return this.getAccountNonExpired();
	}

	// @Override
	public boolean isAccountNonLocked() {
		return this.getAccountNonLocked();
	}

	// @Override
	public boolean isCredentialsNonExpired() {
		return this.getCredentialsNonExpired();
	}

	// @Override
	public boolean isEnabled() {
		return this.getEnabled();
	}

	public void setAccountNonExpired(Boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}

	public void setAccountNonLocked(Boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public void setAuthorities(Collection<UsuarioRole> authorities) {
		this.authorities = authorities;
	}

	public void setCredentialsNonExpired(Boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setLastPasswordReset(Date lastPasswordReset) {
		this.lastPasswordReset = lastPasswordReset;
	}

	public void setPassword(String password) {
		// PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		// this.password = passwordEncoder.encode(password);
		this.password = password;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", username=" + username + ", email=" + email + ", password=" + password + ", authorities=" + authorities + "]";
	}

}
