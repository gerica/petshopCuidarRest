package br.com.modulo.cliente.entidade;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tb_pessoa", schema = "cliente")
public class Pessoa implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_pessoa")
	private Long id;

	@Column(name = "ds_nome")
	private String nome;

	@Temporal(TemporalType.DATE)
	@Column(name = "dt_nascimento")
	private Date dtNascimento;

	@Column(name = "ds_sexo")
	private String sexo;

	@Column(name = "ds_tipo_pessoa")
	private String tipoPessoa;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pessoa", cascade = { CascadeType.ALL })
	@JsonIgnore
	private List<Endereco> enderecos;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pessoa", cascade = { CascadeType.ALL })
	@JsonIgnore
	private List<Documento> documentos;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pessoa", cascade = { CascadeType.ALL })
	@JsonIgnore
	private List<Telefone> telefones;

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pessoa other = (Pessoa) obj;
		if (dtNascimento == null) {
			if (other.dtNascimento != null)
				return false;
		} else if (!dtNascimento.equals(other.dtNascimento))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (sexo == null) {
			if (other.sexo != null)
				return false;
		} else if (!sexo.equals(other.sexo))
			return false;
		if (tipoPessoa == null) {
			if (other.tipoPessoa != null)
				return false;
		} else if (!tipoPessoa.equals(other.tipoPessoa))
			return false;
		return true;
	}

	public List<Documento> getDocumentos() {
		return documentos;
	}

	public Date getDtNascimento() {
		return dtNascimento;
	}

	public List<Endereco> getEnderecos() {
		return enderecos;
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getSexo() {
		return sexo;
	}

	public List<Telefone> getTelefones() {
		return telefones;
	}

	public String getTipoPessoa() {
		return tipoPessoa;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dtNascimento == null) ? 0 : dtNascimento.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((sexo == null) ? 0 : sexo.hashCode());
		result = prime * result + ((tipoPessoa == null) ? 0 : tipoPessoa.hashCode());
		return result;
	}

	public void setDocumentos(List<Documento> documentos) {
		this.documentos = documentos;
	}

	public void setDtNascimento(Date dtNascimento) {
		this.dtNascimento = dtNascimento;
	}

	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public void setTelefones(List<Telefone> telefones) {
		this.telefones = telefones;
	}

	public void setTipoPessoa(String tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
	}

	@Override
	public String toString() {
		return "Pessoa [id=" + id + ", nome=" + nome + ", dtNascimento=" + dtNascimento + ", sexo=" + sexo
				+ ", tipoPessoa=" + tipoPessoa + ", enderecos=" + enderecos + ", documentos=" + documentos
				+ ", telefones=" + telefones + "]";
	}

}
