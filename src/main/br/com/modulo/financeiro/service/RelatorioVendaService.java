package br.com.modulo.financeiro.service;

import br.com.compartilhado.execao.PetShopBusinessException;
import br.com.modulo.financeiro.controller.wrapper.RelatorioVendaWrapper;

public interface RelatorioVendaService {

	void consultar(RelatorioVendaWrapper wrapper) throws PetShopBusinessException;

}
