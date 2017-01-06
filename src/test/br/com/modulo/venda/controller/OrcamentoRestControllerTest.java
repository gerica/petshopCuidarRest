package br.com.modulo.venda.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThat;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.compartilhado.controller.model.SuccessResponse;
import br.com.config.Application;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = WebEnvironment.RANDOM_PORT)
public class OrcamentoRestControllerTest {

	@Autowired
	private TestRestTemplate restTemplate;

	private JacksonTester<SuccessResponse> json;

	@Before
	public void setUp() {

		ObjectMapper objectMapper = new ObjectMapper();
		// Possibly configure the mapper
		JacksonTester.initFields(this, objectMapper);

	}

	@Test
	public void test() throws IOException {
//		SuccessResponse success = new SuccessResponse("Rest", "Serviços");
		SuccessResponse success = restTemplate.getForObject("/auth", SuccessResponse.class);
		
//		assertThat(this.json.write(success)).hasJsonPathStringValue("Serviços");

	}

}
