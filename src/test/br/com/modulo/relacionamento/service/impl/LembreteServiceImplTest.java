package br.com.modulo.relacionamento.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.BDDMockito.given;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.compartilhado.entidade.Usuario;
import br.com.compartilhado.execao.PetShopBusinessException;
import br.com.compartilhado.service.AuthenticationService;
import br.com.config.Application;
import br.com.modulo.cliente.entidade.Pessoa;
import br.com.modulo.cliente.service.PessoaService;
import br.com.modulo.relacionamento.entidade.Lembrete;
import br.com.modulo.relacionamento.entidade.enums.StatusLembreteEnum;
import br.com.modulo.relacionamento.repository.LembreteRepository;
import br.com.modulo.relacionamento.service.LembreteService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = WebEnvironment.RANDOM_PORT)
public class LembreteServiceImplTest {

	@Autowired
	private LembreteService service;

	@MockBean
	private PessoaService pessoaService;

	@MockBean
	private AuthenticationService authenticationService;


	@MockBean
	private LembreteRepository repository;

	private Long idPessoa = new Long(1);

	@Test
	public void gravar() throws PetShopBusinessException {
		Lembrete lembrete = service.gravar(criarLembrete(), idPessoa);
		assertNotNull(lembrete.getDtCadastro());
		assertNotNull(lembrete.getDtLembrete());
		assertNotNull(lembrete.getPessoa());
		assertNotNull(lembrete.getUsuario());
		assertNotNull(lembrete.getObservacao());
		assertNotNull(lembrete.getDtLembrete());
		assertEquals(lembrete.getStatus(), StatusLembreteEnum.ABERTO);
	}

	@Before
	public void setUp() throws PetShopBusinessException {
		given(pessoaService.findById(idPessoa)).willReturn(new Pessoa());
		given(authenticationService.get()).willReturn(new Usuario());

	}

	private Lembrete criarLembrete() {
		Lembrete obj = new Lembrete();
		obj.setObservacao("Retornar em 2 meses");
		obj.setDtLembrete(new Date());

		return obj;
	}

}
