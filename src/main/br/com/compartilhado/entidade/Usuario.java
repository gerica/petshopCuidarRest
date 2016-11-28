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

	@Column(name = "dt_last_password_reset")
	private Date lastPasswordReset;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "usuario", cascade = { CascadeType.ALL })
	// @JsonIgnore
	private Collection<UsuarioRole> authorities;

	@Transient
	@JsonIgnore
	private Boolean accountNonExpired = true;

	@Column(name = "in_account_locked")
	private Boolean accountLocked = false;

	@Transient
	@JsonIgnore
	private Boolean credentialsNonExpired = true;

	@Column(name = "is_enabled")
	private Boolean enabled = false;

	@Transient
	private String tempPassword;

	public Usuario() {

	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (enabled == null) {
			if (other.enabled != null)
				return false;
		} else if (!enabled.equals(other.enabled))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	public Boolean getAccountLocked() {
		return accountLocked;
	}

	public Boolean getAccountNonExpired() {
		return this.accountNonExpired;
	}

	// @Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authorities;
	}

	public Boolean getCredentialsNonExpired() {
		return this.credentialsNonExpired;
	}

	public String getEmail() {
		return email;
	}

	public Boolean getEnabled() {
		return this.enabled;
	}

	public Long getId() {
		return id;
	}

	public Date getLastPasswordReset() {
		return lastPasswordReset;
	}

	public String getPassword() {
		return password;
	}

	public String getTempPassword() {
		return tempPassword;
	}

	public String getUsername() {
		return username;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((enabled == null) ? 0 : enabled.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	public boolean isAccountNonExpired() {
		return this.getAccountNonExpired();
	}

	public boolean isAccountNonLocked() {
		return !this.getAccountLocked();
	}

	public boolean isCredentialsNonExpired() {
		return this.getCredentialsNonExpired();
	}

	public boolean isEnabled() {
		return this.getEnabled();
	}

	public void setAccountLocked(Boolean accountLocked) {
		this.accountLocked = accountLocked;
	}

	public void setAccountNonExpired(Boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
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
		this.password = password;
	}

	public void setTempPassword(String tempPassword) {
		this.tempPassword = tempPassword;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", username=" + username + ", email=" + email + ", password=" + password + ", lastPasswordReset=" + lastPasswordReset + ", authorities="
				+ authorities + ", accountNonExpired=" + accountNonExpired + ", accountLocked=" + accountLocked + ", credentialsNonExpired=" + credentialsNonExpired + ", enabled="
				+ enabled + ", tempPassword=" + tempPassword + "]";
	}

}
