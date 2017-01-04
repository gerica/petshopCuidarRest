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
import br.com.modulo.venda.entidade.ProdutoCliente;
import br.com.modulo.venda.repository.OrcamentoRepository;
import br.com.modulo.venda.service.OrcamentoService;
import br.com.modulo.venda.service.ProdutoClienteService;
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
	private ProdutoClienteService produtoClienteService;

	@Autowired
	private LoteService loteService;

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
		if (!UtilsEmpty.isEmpty(orcamento.getProduto())) {
			ItemVenda item = null;
			for (ProdutoCliente pc : orcamento.getProduto()) {
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

	private Long getQuatidadeProduto(List<? extends Lote> lotes) {
		Long qtd = new Long(0);
		for (Lote lote : lotes) {
			qtd += lote.getQuantidade();
		}

		return qtd;
	}

	private Double getValorProduto(List<? extends Lote> lotes) {
		Double valor = 0.0;
		int qtd = 0;
		for (Lote lote : lotes) {
			qtd++;
			valor += lote.getValor();
		}

		return valor / qtd;
	}

	private void getValorVenda(ItemVenda item, Produto produto, Long quantidade) throws PetShopBusinessException {
		logger.info("OrcamentoServiceImpl.getLote()");

		List<? extends Lote> lotes = loteService.findByIdProduto(produto.getId(), produto.getTipoProduto());
		if (!UtilsEmpty.isEmpty(lotes)) {
			item.setValorVenda(getValorVenda(quantidade, lotes));
			item.setQuantidade(getQuatidadeProduto(lotes));
			item.setValor(getValorProduto(lotes));
		} else {
//			throw new PetShopBusinessException("O produto, " + produto.getNome() + "	 está esgotado no estoque.");
		}

	}

	private Double getValorVenda(Long quantidade, List<? extends Lote> lotes) throws PetShopBusinessException {
		Double result = 0.0;
		for (Lote l : lotes) {
			if (l.getQuantidade() >= quantidade) {
				result += l.getValorVenda() * quantidade;
				return result;
			} else {
				result = l.getValorVenda() * l.getQuantidade();
				quantidade = quantidade - l.getQuantidade();
			}
		}
		throw new PetShopBusinessException("O produto não tem a quantidade necessária no estoque.");
	}

	private void validar(Pessoa pessoa, List<ItemVenda> itens) throws PetShopBusinessException {
		if (UtilsEmpty.isEmpty(pessoa) || UtilsEmpty.isEmpty(pessoa.getId())) {
			throw new PetShopBusinessException("Informe o cliente.");
		}

		if (UtilsEmpty.isEmpty(itens)) {
			throw new PetShopBusinessException("Informe o(s) produto(s).");
		}
	}

}
