package br.com.modulo.financeiro.repository;

import java.util.List;

import br.com.modulo.financeiro.entidade.RelatorioVenda;

public interface RelatorioVendaRepository {

	List<RelatorioVenda> consultarMensal(Integer ano, String periodoValor);

	List<RelatorioVenda> consultarBimenstral(Integer ano, String periodoValor);

	List<RelatorioVenda> consultarAnual(Integer ano);

	List<RelatorioVenda> consultarSimenstral(Integer ano, String periodoValor);

	List<RelatorioVenda> consultarTrimenstral(Integer ano, String periodoValor);

}
