package br.com.modulo.venda.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	public List<OrcamentoWrapper> findAll() throws PetShopBusinessException {
		logger.info("OrcamentoServiceImpl.findAll()");
		List<Orcamento> orcamentos = (List<Orcamento>) repository.findAll();
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
	public Long findCount() throws PetShopBusinessException {
		return repository.count();
	}

	@Override
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
		return orcamento;

	}

	private List<ItemVenda> criarItemVenda(Orcamento orcamento) throws PetShopBusinessException {
		List<ItemVenda> itens = new ArrayList<ItemVenda>();
		if (!UtilsEmpty.isEmpty(orcamento.getProdutos())) {
			ItemVenda item = null;
			for (ProdutoClienteOrcamento pc : orcamento.getProdutos()) {
				item = new ItemVenda();
				item.setIdProdutoCliente(pc.getId());
				item.setIdProduto(pc.getProduto().getId());
				item.setDesconto(pc.getDesconto());
				item.setQuantidadeVenda(pc.getQuantidade());
				getValorVenda(item, pc.getProduto(), pc.getQuantidade());
				item.setMarca(pc.getProduto().getMarca());
				item.setNome(pc.getProduto().getNome());

				itens.add(item);
			}
		}
		return itens;

	}

	private OrcamentoWrapper criarOrcamentoWrapper(Orcamento orcamento) throws PetShopBusinessException {
		OrcamentoWrapper wrapper;
		wrapper = new OrcamentoWrapper();
		wrapper.setId(orcamento.getId());
		wrapper.setPessoa(orcamento.getPessoa());
		wrapper.setDtOrcamento(orcamento.getDtOrcamento());
		wrapper.setItens(criarItemVenda(orcamento));
		return wrapper;
	}

	private void getValorVenda(ItemVenda item, Produto produto, Long quantidade) throws PetShopBusinessException {
		logger.info("OrcamentoServiceImpl.getLote()");

		List<? extends Lote> lotes = loteService.findByIdProduto(produto.getId(), produto.getTipoProduto());
		if (!UtilsEmpty.isEmpty(lotes)) {
			item.setValorVenda(calculoService.getValorVenda(quantidade, lotes));
			item.setQuantidade(calculoService.getQuatidadeProdutos(lotes));
			item.setValor(calculoService.getValorProduto(lotes));
		}

	}

	private void validar(Pessoa pessoa, List<ItemVenda> itens) throws PetShopBusinessException {
		if (UtilsEmpty.isEmpty(pessoa) || UtilsEmpty.isEmpty(pessoa.getId())) {
			throw new PetShopBusinessException("Informe o cliente.");
		}

		if (UtilsEmpty.isEmpty(itens)) {
			throw new PetShopBusinessException("Informe o(s) produto(s).");
		}
	}

	@Override
	public Orcamento findOrcamentoById(Long idOrcamento) throws PetShopBusinessException {
		return repository.findOne(idOrcamento);
	}

}
