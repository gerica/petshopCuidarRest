package br.com.modulo.relacionamento.entidade;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.compartilhado.entidade.Usuario;
import br.com.modulo.cliente.entidade.Pessoa;
import br.com.modulo.relacionamento.entidade.enums.StatusLembreteEnum;

@Entity
@Table(name = "tb_lembrete", schema = "relacionamento")
public class Lembrete implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "id_pessoa")
	private Pessoa pessoa;

	@ManyToOne
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;

	@Temporal(TemporalType.DATE)
	@Column(name = "dt_lembrete")
	private Date dtLembrete;

	@Temporal(TemporalType.DATE)
	@Column(name = "dt_cadastro")
	private Date dtCadastro;

	@Column(name = "ds_observacao")
	private String observacao;

	@Column(name = "ds_status")
	@Enumerated(EnumType.STRING)
	private StatusLembreteEnum status = StatusLembreteEnum.ABERTO;

	public Date getDtCadastro() {
		return dtCadastro;
	}

	public Date getDtLembrete() {
		return dtLembrete;
	}

	public Long getId() {
		return id;
	}

	public String getObservacao() {
		return observacao;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public StatusLembreteEnum getStatus() {
		return status;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setDtCadastro(Date dtCadastro) {
		this.dtCadastro = dtCadastro;
	}

	public void setDtLembrete(Date dtLembrete) {
		this.dtLembrete = dtLembrete;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public void setStatus(StatusLembreteEnum status) {
		this.status = status;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
