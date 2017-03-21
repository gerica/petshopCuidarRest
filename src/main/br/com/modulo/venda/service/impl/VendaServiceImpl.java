package br.com.modulo.venda.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.compartilhado.execao.PetShopBusinessException;
import br.com.modulo.produto.entidade.Lote;
import br.com.modulo.produto.service.LoteService;
import br.com.modulo.venda.entidade.Orcamento;
import br.com.modulo.venda.entidade.ProdutoClienteOrcamento;
import br.com.modulo.venda.entidade.Venda;
import br.com.modulo.venda.repository.VendaRepository;
import br.com.modulo.venda.service.CalculoVendaService;
import br.com.modulo.venda.service.OrcamentoService;
import br.com.modulo.venda.service.ProdutoClienteVendaService;
import br.com.modulo.venda.service.VendaService;
import br.com.util.UtilsEmpty;

@Service
public class VendaServiceImpl implements VendaService {

	private static final Logger logger = LoggerFactory.getLogger(VendaServiceImpl.class);

	@Autowired
	private VendaRepository repository;

	@Autowired
	private OrcamentoService orcamentoService;

	@Autowired
	private CalculoVendaService calculoService;

	@Autowired
	private LoteService loteService;

	@Autowired
	private ProdutoClienteVendaService produtoClienteService;

	@Override
	public List<Venda> findAll() throws PetShopBusinessException {
		logger.info("VendaServiceImpl.findAll()");
		return null;
	}

	@Override
	public Venda findById(Long idVenda) throws PetShopBusinessException {
		logger.info("VendaServiceImpl.findById()");
		return null;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Venda gravar(Long idOrcamento) throws PetShopBusinessException {
		logger.info("VendaServiceImpl.gravar()");
		validarVenda(idOrcamento);
		Orcamento orcamento = orcamentoService.findOrcamentoById(idOrcamento);

		orcamentoService.validarOrcamento(orcamento);
		Venda venda = new Venda();
		venda.setQuantidade(new Long(0));
		venda.setValorTotal(new Double(0));
		venda.setPessoa(orcamento.getPessoa());
		venda.setUsuario(orcamento.getUsuario());
		venda.setDtVenda(new Date());
		venda.setOrcamento(orcamento);
		getValoresProduto(venda, orcamento);
		repository.save(venda);
		produtoClienteService.gravar(venda, orcamento.getProdutos());
		orcamentoService.realizar(idOrcamento);

		return venda;

	}

	private void getValoresProduto(Venda venda, Orcamento orcamento) throws PetShopBusinessException {

		if (!UtilsEmpty.isEmpty(orcamento.getProdutos())) {
			for (ProdutoClienteOrcamento pc : orcamento.getProdutos()) {
				List<? extends Lote> lotes = loteService.findByIdProduto(pc.getProduto().getId(),
						pc.getProduto().getTipoProduto());

				venda.setQuantidade(venda.getQuantidade() + pc.getQuantidade());
				venda.setValorTotal(
						venda.getValorTotal() + calculoService.getValorVenda(pc.getQuantidade(), lotes, true));
				venda.setDesconto(pc.getDesconto());

			}
		}

	}

	private void validarVenda(Long idOrcamento) throws PetShopBusinessException {
		if (UtilsEmpty.isEmpty(idOrcamento)) {
			throw new PetShopBusinessException("O orçamento é obrigatório para realizar a venda.");
		}
	}

}
