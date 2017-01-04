package br.com.modulo.venda.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.compartilhado.execao.PetShopBusinessException;
import br.com.modulo.venda.entidade.ProdutoClienteOrcamento;
import br.com.modulo.venda.entidade.Venda;
import br.com.modulo.venda.service.ProdutoClienteVendaService;

public class ProdutoClienteVendaServiceImpl implements ProdutoClienteVendaService {

	private static final Logger logger = LoggerFactory.getLogger(ProdutoClienteVendaServiceImpl.class);

	@Override
	public void gravar(Venda venda, ProdutoClienteOrcamento itens) throws PetShopBusinessException {
		logger.info("ProdutoClienteVendaServiceImpl.gravar()");

	}

}
