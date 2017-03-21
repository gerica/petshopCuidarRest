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
import br.com.modulo.venda.entidade.ProdutoClienteOrcamento;
import br.com.modulo.venda.repository.ProdutoClienteOrcamentoRepository;
import br.com.modulo.venda.service.ProdutoClienteOrcamentoService;

@Service
public class ProdutoClienteOrcamentoServiceImpl implements ProdutoClienteOrcamentoService {

	private static final Logger logger = LoggerFactory.getLogger(ProdutoClienteOrcamentoServiceImpl.class);

	@Autowired
	private ProdutoClienteOrcamentoRepository repository;

	@Autowired
	private ProdutoService produtoService;

	public void excluir(Long idOrcamento) throws PetShopBusinessException {
		repository.deleteByIdOrcamento(idOrcamento);
	}

	@Override
	public List<ProdutoClienteOrcamento> findByOrcamento(Orcamento orcamento) throws PetShopBusinessException {
		return repository.findByOrcamento(orcamento);
	}

	@Override
	public void gravar(Orcamento orcamento, List<ItemVenda> itens) throws PetShopBusinessException {
		logger.info("ProdutoClienteServiceImpl.gravar()");
		excluir(orcamento.getId());

		for (ItemVenda itemVenda : itens) {
			ProdutoClienteOrcamento produtoCliente = new ProdutoClienteOrcamento();
			Produto produto = produtoService.findById(itemVenda.getIdProduto());
			produtoCliente.setProduto(produto);
			produtoCliente.setQuantidade(itemVenda.getQuantidadeVenda());
			produtoCliente.setOrcamento(orcamento);
			produtoCliente.setDesconto(itemVenda.getDesconto());
			repository.save(produtoCliente);
		}

	}
	
	

}
