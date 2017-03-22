package br.com.modulo.venda.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.RollbackException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.compartilhado.execao.PetShopBusinessException;
import br.com.compartilhado.service.AuthenticationService;
import br.com.modulo.cliente.entidade.Pessoa;
import br.com.modulo.cliente.service.PessoaService;
import br.com.modulo.produto.entidade.Lote;
import br.com.modulo.produto.entidade.Produto;
import br.com.modulo.produto.service.LoteService;
import br.com.modulo.venda.controller.wrapper.ItemVenda;
import br.com.modulo.venda.controller.wrapper.OrcamentoWrapper;
import br.com.modulo.venda.entidade.Orcamento;
import br.com.modulo.venda.entidade.ProdutoClienteOrcamento;
import br.com.modulo.venda.entidade.enums.StatusOrcamentoEnum;
import br.com.modulo.venda.repository.OrcamentoRepository;
import br.com.modulo.venda.service.CalculoVendaService;
import br.com.modulo.venda.service.OrcamentoService;
import br.com.modulo.venda.service.ProdutoClienteOrcamentoService;
import br.com.util.UtilsEmpty;

@Service
public class OrcamentoServiceImpl implements OrcamentoService {

	private static final Logger logger = LoggerFactory.getLogger(OrcamentoServiceImpl.class);

	@Autowired
	private OrcamentoRepository repository;

	@Autowired
	private AuthenticationService authenticationService;

	@Autowired
	private PessoaService pessoaService;

	@Autowired
	private ProdutoClienteOrcamentoService produtoClienteService;

	@Autowired
	private LoteService loteService;

	@Autowired
	private CalculoVendaService calculoService;

	@Override
	public void excluir(Long idOrcamento) throws PetShopBusinessException {
		logger.info("OrcamentoServiceImpl.excluir()");
		produtoClienteService.excluir(idOrcamento);
		repository.delete(idOrcamento);

	}

	@Override
	public void fechar(Long idOrcamento) throws PetShopBusinessException {
		Orcamento objDB = repository.findOne(idOrcamento);
		objDB.setStatus(StatusOrcamentoEnum.FECHADO);
		repository.save(objDB);
	}

	@Override
	public List<OrcamentoWrapper> findAll() throws PetShopBusinessException {
		logger.info("OrcamentoServiceImpl.findAll()");
		List<Orcamento> orcamentos = (List<Orcamento>) repository.findByStatus(StatusOrcamentoEnum.ABERTO);
		List<OrcamentoWrapper> result = new ArrayList<OrcamentoWrapper>();

		for (Orcamento orcamento : orcamentos) {
			result.add(criarOrcamentoWrapper(orcamento));

		}
		return result;
	}

	@Override
	public OrcamentoWrapper findById(Long idOrcamento) throws PetShopBusinessException {
		return criarOrcamentoWrapper(repository.findOne(idOrcamento));
	}

	@Override
	public Long findCountAberto() throws PetShopBusinessException {
		return repository.countByStatus(StatusOrcamentoEnum.ABERTO);
	}

	@Override
	public Orcamento findOrcamentoById(Long idOrcamento) throws PetShopBusinessException {
		return repository.findOne(idOrcamento);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Orcamento gravar(Long idOrcamento, Pessoa pessoa, List<ItemVenda> itens) throws PetShopBusinessException {
		logger.info("OrcamentoServiceImpl.gravar()");

		validar(pessoa, itens);

		Orcamento orcamento = null;
		if (!UtilsEmpty.isEmpty(idOrcamento)) {
			orcamento = repository.findOne(idOrcamento);
		}

		if (orcamento == null) {
			orcamento = new Orcamento();
			orcamento.setPessoa(pessoaService.findById(pessoa.getId()));
			orcamento.setDtOrcamento(new Date());
			orcamento.setUsuario(authenticationService.get());
		}
		repository.save(orcamento);
		produtoClienteService.gravar(orcamento, itens);
		validarOrcamento(orcamento);
		return orcamento;

	}

	@Override
	public void realizar(Long idOrcamento) throws PetShopBusinessException {
		Orcamento objDB = repository.findOne(idOrcamento);
		objDB.setStatus(StatusOrcamentoEnum.REALIZADO);
		repository.save(objDB);
	}

	@Transactional(rollbackFor = Exception.class)
	public void validarOrcamento(Orcamento orcamento) throws PetShopBusinessException {

		List<ProdutoClienteOrcamento> prodClienteList = produtoClienteService.findByOrcamento(orcamento);

		if (UtilsEmpty.isEmpty(prodClienteList)) {
			throw new PetShopBusinessException("Para realizar a venda é necessário ter produtos..");
		}

		for (ProdutoClienteOrcamento pc : prodClienteList) {

			List<? extends Lote> lotes = loteService.findByIdProduto(pc.getProduto().getId(),
					pc.getProduto().getTipoProduto());

			if (UtilsEmpty.isEmpty(lotes)) {
				throw new PetShopBusinessException("Para realizar a venda é necessário estoque do produto.");
			}

			try {
				loteService.validarQuantidade(pc.getQuantidade(), lotes);

			} catch (RollbackException e) {
				throw new PetShopBusinessException(e.getMessage());
			} catch (PetShopBusinessException e) {
				throw e;
			} catch (Exception e) {
				throw new PetShopBusinessException(e);
			}

		}

	}

	private List<ItemVenda> criarItemVenda(OrcamentoWrapper wrapper, Orcamento orcamento)
			throws PetShopBusinessException {
		List<ItemVenda> itens = new ArrayList<ItemVenda>();
		List<ProdutoClienteOrcamento> prodClienteList = produtoClienteService.findByOrcamento(orcamento);
		Double valorOrcamento = 0.0;
		if (!UtilsEmpty.isEmpty(prodClienteList)) {
			ItemVenda item = null;
			for (ProdutoClienteOrcamento pc : prodClienteList) {
				item = new ItemVenda();
				item.setIdProdutoCliente(pc.getId());
				item.setIdProduto(pc.getProduto().getId());
				item.setDesconto(pc.getDesconto());
				item.setQuantidadeVenda(pc.getQuantidade());
				valorOrcamento += getValorVenda(item, pc.getProduto(), pc.getQuantidade());

				item.setMarca(pc.getProduto().getMarca());
				item.setNome(pc.getProduto().getNome());

				itens.add(item);
			}
		}
		wrapper.setValorOrcamento(valorOrcamento);
		return itens;

	}

	private OrcamentoWrapper criarOrcamentoWrapper(Orcamento orcamento) throws PetShopBusinessException {
		OrcamentoWrapper wrapper;
		wrapper = new OrcamentoWrapper();
		wrapper.setId(orcamento.getId());
		wrapper.setPessoa(orcamento.getPessoa());
		wrapper.setDtOrcamento(orcamento.getDtOrcamento());
		wrapper.setItens(criarItemVenda(wrapper, orcamento));
		return wrapper;
	}

	private Double getValorVenda(ItemVenda item, Produto produto, Long quantidade) throws PetShopBusinessException {
		logger.info("OrcamentoServiceImpl.getLote()");

		Double valorOrcamentoItem = 0.0;

		List<? extends Lote> lotes = loteService.findByIdProduto(produto.getId(), produto.getTipoProduto());
		if (!UtilsEmpty.isEmpty(lotes)) {
			try {
				item.setValorVenda(getValorVenda(quantidade, lotes));
				valorOrcamentoItem = calculoService.getValorVenda(quantidade, lotes, false);
			} catch (PetShopBusinessException e) {
				e.printStackTrace();
			}
			item.setQuantidade(calculoService.getQuatidadeProdutos(lotes));
			item.setValor(calculoService.getValorProduto(lotes));
		}
		return valorOrcamentoItem;

	}

	private void validar(Pessoa pessoa, List<ItemVenda> itens) throws PetShopBusinessException {
		if (UtilsEmpty.isEmpty(pessoa) || UtilsEmpty.isEmpty(pessoa.getId())) {
			throw new PetShopBusinessException("Informe o cliente.");
		}

		if (UtilsEmpty.isEmpty(itens)) {
			throw new PetShopBusinessException("Informe o(s) produto(s).");
		}
	}

	@Transactional(rollbackFor = Exception.class)
	private Double getValorVenda(Long quantidade, List<? extends Lote> lotes)
			throws PetShopBusinessException, RollbackException {

		for (Lote l : lotes) {
			if (l.getQuantidade() >= quantidade) {
				return l.getValorVenda();
			} else {
				return l.getValorVenda();
			}
		}
		throw new PetShopBusinessException("O produto não tem a quantidade necessária no estoque.");
	}

}
