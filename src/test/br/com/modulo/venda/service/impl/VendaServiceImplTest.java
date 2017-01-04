package br.com.modulo.venda.service.impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.modulo.venda.service.OrcamentoService;
import br.com.modulo.venda.service.VendaService;

//@RunWith(SpringJUnit4ClassRunner.class)   // 1
//@SpringApplicationConfiguration(classes = Application.class)   // 2
//@WebAppConfiguration   // 3
//@IntegrationTest("server.port:0")   // 4

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class VendaServiceImplTest {

	@Autowired
	private VendaService vendaService;

	@MockBean
	private OrcamentoService orcamentoService;

	@Before
	public void setUp() {
		System.out.println("setUp");
//		given(this.orcamentoService.findOrcamentoById(new Long(1))).willReturn(new Orcamento());
	}

	@Test
	public void test() {
		System.out.println(vendaService);
	}

	

}
