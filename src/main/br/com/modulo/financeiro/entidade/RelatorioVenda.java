package br.com.modulo.financeiro.entidade;

import java.util.Date;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.SqlResultSetMapping;

@Entity
@SqlResultSetMapping(name = "RelatorioVendaMapping", //
		classes = @ConstructorResult(targetClass = RelatorioVenda.class, //
				columns = { @ColumnResult(name = "idVenda", type = Long.class), //
						@ColumnResult(name = "desconto", type = Double.class), //
						@ColumnResult(name = "dtVenda", type = Date.class), //
						@ColumnResult(name = "quantidade", type = Long.class),
						@ColumnResult(name = "valorTotal", type = Double.class), //
						@ColumnResult(name = "nomePessoa", type = String.class),
						@ColumnResult(name = "nomeUsuario", type = String.class),
						@ColumnResult(name = "dtOrcamento", type = Date.class) }))
public class RelatorioVenda {

	@Id
	private Long idVenda;
	private Double desconto;
	private Date dtVenda;
	private Long quantidade;
	private Double valorTotal;
	private String nomePessoa;
	private String nomeUsuario;
	private Date dtOrcamento;

	public RelatorioVenda(Long idVenda, Double desconto, Date dtVenda, Long quantidade, Double valorTotal,
			String nomePessoa, String nomeUsuario, Date dtOrcamento) {
		super();
		this.idVenda = idVenda;
		this.desconto = desconto;
		this.dtVenda = dtVenda;
		this.quantidade = quantidade;
		this.valorTotal = valorTotal;
		this.nomePessoa = nomePessoa;
		this.nomeUsuario = nomeUsuario;
		this.dtOrcamento = dtOrcamento;
	}

	public Long getIdVenda() {
		return idVenda;
	}

	public void setIdVenda(Long idVenda) {
		this.idVenda = idVenda;
	}

	public Double getDesconto() {
		return desconto;
	}

	public void setDesconto(Double desconto) {
		this.desconto = desconto;
	}

	public Date getDtVenda() {
		return dtVenda;
	}

	public void setDtVenda(Date dtVenda) {
		this.dtVenda = dtVenda;
	}

	public Long getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Long quantidade) {
		this.quantidade = quantidade;
	}

	public Double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public String getNomePessoa() {
		return nomePessoa;
	}

	public void setNomePessoa(String nomePessoa) {
		this.nomePessoa = nomePessoa;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public Date getDtOrcamento() {
		return dtOrcamento;
	}

	public void setDtOrcamento(Date dtOrcamento) {
		this.dtOrcamento = dtOrcamento;
	}

	@Override
	public String toString() {
		return "RelatorioVendaWrapperOut [idVenda=" + idVenda + ", desconto=" + desconto + ", dtVenda=" + dtVenda
				+ ", quantidade=" + quantidade + ", valorTotal=" + valorTotal + ", nomePessoa=" + nomePessoa
				+ ", nomeUsuario=" + nomeUsuario + ", dtOrcamento=" + dtOrcamento + "]";
	}

}
