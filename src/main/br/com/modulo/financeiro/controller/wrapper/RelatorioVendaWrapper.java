package br.com.modulo.financeiro.controller.wrapper;

import java.util.Date;

import br.com.modulo.financeiro.entidade.enums.RelatorioPeriodoTipoEnum;

/**
 * @author rogerio
 *
 */
public class RelatorioVendaWrapper {

	private String periodoValor;
	private Integer ano;
	private RelatorioPeriodoTipoEnum periodo;
	private Date dtInicio;
	private Date dtFinal;

	public Integer getAno() {
		return ano;
	}

	public Date getDtFinal() {
		return dtFinal;
	}

	public Date getDtInicio() {
		return dtInicio;
	}

	public RelatorioPeriodoTipoEnum getPeriodo() {
		return periodo;
	}

	public String getPeriodoValor() {
		return periodoValor;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public void setDtFinal(Date dtFinal) {
		this.dtFinal = dtFinal;
	}

	public void setDtInicio(Date dtInicio) {
		this.dtInicio = dtInicio;
	}

	public void setPeriodo(RelatorioPeriodoTipoEnum periodo) {
		this.periodo = periodo;
	}

	public void setPeriodoValor(String periodoValor) {
		this.periodoValor = periodoValor;
	}

}
