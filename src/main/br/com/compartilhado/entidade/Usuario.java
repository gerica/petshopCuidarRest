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

	public Usuario() {

	}

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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	// public String getAuthoritiesBd() {
	// return authoritiesBd;
	// }
	//
	// public void setAuthoritiesBd(String authoritiesBd) {
	// this.authoritiesBd = authoritiesBd;
	// }

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", username=" + username + ", email=" + email + ", password=" + password
				+ ", authorities=" + authorities + "]";
	}

	public void setPassword(String password) {
		// PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		// this.password = passwordEncoder.encode(password);
		this.password = password;
	}

	// @Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authorities;
	}

	public void setAuthorities(Collection<UsuarioRole> authorities) {
		this.authorities = authorities;
	}

	@JsonIgnore
	public Boolean getAccountNonExpired() {
		return this.accountNonExpired;
	}

	public void setAccountNonExpired(Boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}

	// @Override
	public boolean isAccountNonExpired() {
		return this.getAccountNonExpired();
	}

	@JsonIgnore
	public Boolean getAccountNonLocked() {
		return this.accountNonLocked;
	}

	public void setAccountNonLocked(Boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}

	// @Override
	public boolean isAccountNonLocked() {
		return this.getAccountNonLocked();
	}

	@JsonIgnore
	public Boolean getCredentialsNonExpired() {
		return this.credentialsNonExpired;
	}

	public void setCredentialsNonExpired(Boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}

	// @Override
	public boolean isCredentialsNonExpired() {
		return this.getCredentialsNonExpired();
	}

	@JsonIgnore
	public Boolean getEnabled() {
		return this.enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	// @Override
	public boolean isEnabled() {
		return this.getEnabled();
	}

	public Date getLastPasswordReset() {
		return lastPasswordReset;
	}

	public void setLastPasswordReset(Date lastPasswordReset) {
		this.lastPasswordReset = lastPasswordReset;
	}

}
