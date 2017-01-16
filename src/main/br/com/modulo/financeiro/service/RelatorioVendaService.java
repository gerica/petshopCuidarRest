package br.com.modulo.financeiro.service;

import java.util.List;

import br.com.compartilhado.execao.PetShopBusinessException;
import br.com.modulo.financeiro.controller.wrapper.RelatorioVendaWrapper;
import br.com.modulo.financeiro.entidade.RelatorioVenda;

public interface RelatorioVendaService {

	List<RelatorioVenda> consultar(RelatorioVendaWrapper wrapper) throws PetShopBusinessException;

}
