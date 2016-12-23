package br.com.modulo.produto.entidade;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Lote implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_lote")
	private Long id;

	@Column(name = "nr_numero")
	private Long numero;

	@Temporal(TemporalType.DATE)
	@Column(name = "dt_lote")
	private Date dataLote;

	@Temporal(TemporalType.DATE)
	@Column(name = "dt_validade")
	private Date dataValidade;

	@Column(name = "nr_valor")
	private Double valor;

	@Column(name = "nr_valor_venda")
	private Double valorVenda;

	@Column(name = "nr_quantidade")
	private Double quantidade;

	public Date getDataLote() {
		return dataLote;
	}

	public Date getDataValidade() {
		return dataValidade;
	}

	public Long getId() {
		return id;
	}

	public Long getNumero() {
		return numero;
	}

	public Double getQuantidade() {
		return quantidade;
	}

	public Double getValor() {
		return valor;
	}

	public Double getValorVenda() {
		return valorVenda;
	}

	public void setDataLote(Date dataLote) {
		this.dataLote = dataLote;
	}

	public void setDataValidade(Date dataValidade) {
		this.dataValidade = dataValidade;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setNumero(Long numero) {
		this.numero = numero;
	}

	public void setQuantidade(Double quantidade) {
		this.quantidade = quantidade;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public void setValorVenda(Double valorVenda) {
		this.valorVenda = valorVenda;
	}

}
