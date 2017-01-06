package br.com.config;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ApplicationTest {

	@Autowired
	private TestRestTemplate restTemplate;

	@Before
	public void setUp() {
		System.out.println("setUp");
		// given(this.orcamentoService.findOrcamentoById(new
		// Long(1))).willReturn(new Orcamento());
	}

	@Test
	public void test() {
//		String info  = restTemplate.getForObject("/auth", String.class,"teste");		
//		
////		assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
//		assertEquals("Serviços", info);
		System.out.println("vai");
	}

}
