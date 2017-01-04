package br.com.modulo.venda.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.compartilhado.execao.PetShopBusinessException;
import br.com.modulo.produto.entidade.Produto;
import br.com.modulo.produto.service.ProdutoService;
import br.com.modulo.venda.controller.wrapper.ItemVenda;
import br.com.modulo.venda.entidade.Orcamento;
import br.com.modulo.venda.entidade.ProdutoCliente;
import br.com.modulo.venda.repository.ProdutoClienteRepository;
import br.com.modulo.venda.service.ProdutoClienteService;

@Service
public class ProdutoClienteServiceImpl implements ProdutoClienteService {

	private static final Logger logger = LoggerFactory.getLogger(ProdutoClienteServiceImpl.class);

	@Autowired
	private ProdutoClienteRepository repository;

	@Autowired
	private ProdutoService produtoService;

	@Override
	public void gravar(Orcamento orcamento, List<ItemVenda> itens) throws PetShopBusinessException {
		logger.info("ProdutoClienteServiceImpl.gravar()");
		apagar(orcamento);

		for (ItemVenda itemVenda : itens) {
			ProdutoCliente produtoCliente = new ProdutoCliente();
			Produto produto = produtoService.findById(itemVenda.getIdProduto());
			produtoCliente.setProduto(produto);
			produtoCliente.setQuantidade(itemVenda.getQuantidadeVenda());
			produtoCliente.setOrcamento(orcamento);
			produtoCliente.setDesconto(itemVenda.getDesconto());
			repository.save(produtoCliente);
		}

	}

	private void apagar(Orcamento orcamento) throws PetShopBusinessException {
		repository.deleteByIdOrcamento(orcamento.getId());
	}

}
