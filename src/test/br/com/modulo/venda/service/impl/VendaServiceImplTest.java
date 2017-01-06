package br.com.modulo.venda.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.BDDMockito.given;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.Utils;
import br.com.compartilhado.execao.PetShopBusinessException;
import br.com.config.Application;
import br.com.modulo.produto.entidade.Lote;
import br.com.modulo.produto.entidade.Medicamento;
import br.com.modulo.produto.entidade.Produto;
import br.com.modulo.produto.entidade.enums.TipoProdutoEnum;
import br.com.modulo.produto.service.LoteService;
import br.com.modulo.venda.entidade.Orcamento;
import br.com.modulo.venda.entidade.ProdutoClienteOrcamento;
import br.com.modulo.venda.entidade.Venda;
import br.com.modulo.venda.repository.VendaRepository;
import br.com.modulo.venda.service.OrcamentoService;
import br.com.modulo.venda.service.VendaService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = WebEnvironment.RANDOM_PORT)
public class VendaServiceImplTest {

	@Autowired
	private VendaService vendaService;

	@MockBean
	private OrcamentoService orcamentoService;

	@MockBean
	private LoteService loteService;

	@MockBean
	private VendaRepository repository;

	private Long idOrcamento = new Long(1);

	@Test
	public void gravarTest() throws PetShopBusinessException {
		Venda venda = vendaService.gravar(new Long(idOrcamento));
		assertNotNull(venda);
		assertEquals(venda.getQuantidade(), new Long(3));
		assertEquals(venda.getDesconto(), new Double(10));
		assertEquals(venda.getValorTotal(), new Double(55));

	}

	@Before
	public void setUp() throws PetShopBusinessException {
		given(this.orcamentoService.findOrcamentoById(idOrcamento)).willReturn(criarOrcamento());

		Mockito.<List<? extends Lote>>when(this.loteService.findByIdProduto(new Long(1), TipoProdutoEnum.MEDICAMENTO))
				.thenReturn(criarLote1());

		Mockito.<List<? extends Lote>>when(this.loteService.findByIdProduto(new Long(2), TipoProdutoEnum.MEDICAMENTO))
				.thenReturn(criarLote2());

	}

	private List<ProdutoClienteOrcamento> criarListaProdutos(Orcamento orcamento) {
		List<ProdutoClienteOrcamento> produtos = new ArrayList<>();

		ProdutoClienteOrcamento p1 = new ProdutoClienteOrcamento();
		p1.setId(new Long(1));
		p1.setOrcamento(orcamento);
		p1.setDesconto(10.0);
		p1.setQuantidade(new Long(1));
		p1.setProduto(Utils.criarMedicamento1());
		produtos.add(p1);

		ProdutoClienteOrcamento p2 = new ProdutoClienteOrcamento();
		p2.setId(new Long(2));
		p2.setOrcamento(orcamento);
		p2.setDesconto(10.0);
		p2.setQuantidade(new Long(2));
		p2.setProduto(Utils.criarMedicamento2());
		produtos.add(p2);

		return produtos;
	}

	private List<? extends Lote> criarLote1() {
		Produto p = Utils.criarMedicamento1();
		List<? extends Lote> result = Utils.criarMedicamentoLote1((Medicamento) p);

		return result;
	}

	private List<? extends Lote> criarLote2() {
		Produto p = Utils.criarMedicamento2();
		List<? extends Lote> result = Utils.criarMedicamentoLote2((Medicamento) p);

		return result;
	}

	private Orcamento criarOrcamento() {
		Orcamento orcamento = new Orcamento();
		orcamento.setDtOrcamento(new Date());
		orcamento.setPessoa(Utils.criarPessoa());
		orcamento.setUsuario(Utils.criarUsuario());
		orcamento.setProduto(criarListaProdutos(orcamento));
		return orcamento;
	}

}
