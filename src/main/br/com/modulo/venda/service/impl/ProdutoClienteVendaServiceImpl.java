package br.com.modulo.venda.service.impl;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.compartilhado.execao.PetShopBusinessException;
import br.com.modulo.produto.entidade.Produto;
import br.com.modulo.venda.entidade.ProdutoClienteOrcamento;
import br.com.modulo.venda.entidade.ProdutoClienteVenda;
import br.com.modulo.venda.entidade.Venda;
import br.com.modulo.venda.repository.ProdutoClienteVendaRepository;
import br.com.modulo.venda.service.ProdutoClienteVendaService;

@Service
public class ProdutoClienteVendaServiceImpl implements ProdutoClienteVendaService {

	private static final Logger logger = LoggerFactory.getLogger(ProdutoClienteVendaServiceImpl.class);

	@Autowired
	private ProdutoClienteVendaRepository repository;

	@Override
	public void gravar(Venda venda, Set<ProdutoClienteOrcamento> itens) throws PetShopBusinessException {
		logger.info("ProdutoClienteVendaServiceImpl.gravar()");

		for (ProdutoClienteOrcamento item : itens) {
			ProdutoClienteVenda produtoCliente = new ProdutoClienteVenda();
			Produto produto = item.getProduto();
			produtoCliente.setProduto(produto);
			produtoCliente.setQuantidade(item.getQuantidade());
			produtoCliente.setVenda(venda);
			repository.save(produtoCliente);
		}

	}

}
