package br.com.modulo.produto.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.compartilhado.execao.PetShopBusinessException;
import br.com.modulo.produto.entidade.Produto;
import br.com.modulo.produto.repository.ProdutoRepository;
import br.com.modulo.produto.service.ProdutoService;

@Service
public class ProdutoServiceImpl implements ProdutoService {

	private static final Logger logger = LoggerFactory.getLogger(ProdutoServiceImpl.class);

	@Autowired
	private ProdutoRepository produtoRepository;

	@Override
	public List<Produto> findByDescricao(String descricao) throws PetShopBusinessException {
		logger.info("ProdutoServiceImpl.findByDescricao()");
		return produtoRepository.findByNomeContainingIgnoreCaseOrMarcaContainingIgnoreCase(descricao, descricao);
	}

	@Override
	public Produto findById(Long id) throws PetShopBusinessException {
		logger.info("ProdutoServiceImpl.findById()");
		return produtoRepository.findById(id);
	}

}
