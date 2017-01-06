package br.com.modulo.venda.service.impl;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.Utils;
import br.com.compartilhado.execao.PetShopBusinessException;
import br.com.config.Application;
import br.com.modulo.produto.entidade.Lote;
import br.com.modulo.produto.entidade.Medicamento;
import br.com.modulo.venda.service.CalculoVendaService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = WebEnvironment.RANDOM_PORT)
public class CalculoVendaServiceImplTest {

	@Autowired
	private CalculoVendaService service;

	private List<Lote> lotes;

	@Test
	public void getQuatidadeProdutos() throws PetShopBusinessException {
		Long qtd = service.getQuatidadeProdutos(lotes);
		assertEquals(qtd, new Long(15));
	}
	
	@Test
	public void getValorProduto() throws PetShopBusinessException {
		Double valor = service.getValorProduto(lotes);
		assertEquals(valor, new Double(75.0));
	}
	
	@Test
	public void getValorVenda() throws PetShopBusinessException {
		Double valor = service.getValorVenda(new Long(5), lotes);
		assertEquals(valor, new Double(75.0));
	}

	@Test
	public void getValorVendaQtdMaiorLote() throws PetShopBusinessException {
		Double valor = service.getValorVenda(new Long(11), lotes);
		assertEquals(valor, new Double(170));
	}

	@Before
	public void setUp() throws PetShopBusinessException {
		Medicamento produto = (Medicamento) Utils.criarMedicamento1();
		lotes = Utils.criarMedicamentoLote1(produto);
		lotes.addAll(Utils.criarMedicamentoLote2(produto));

	}

}
