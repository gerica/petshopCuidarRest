package br.com.modulo.relacionamento.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.compartilhado.execao.PetShopBusinessException;
import br.com.compartilhado.service.AuthenticationService;
import br.com.modulo.cliente.service.PessoaService;
import br.com.modulo.relacionamento.entidade.Lembrete;
import br.com.modulo.relacionamento.entidade.enums.StatusLembreteEnum;
import br.com.modulo.relacionamento.repository.LembreteRepository;
import br.com.modulo.relacionamento.service.LembreteService;

@Service
public class LembreteServiceImpl implements LembreteService {

	private static final Logger logger = LoggerFactory.getLogger(LembreteServiceImpl.class);

	@Autowired
	private LembreteRepository repository;

	@Autowired
	private PessoaService pessoaService;

	@Autowired
	private AuthenticationService authenticationService;

	@Override
	public void excluir(Long idLembrete) throws PetShopBusinessException {
		logger.info("LembreteServiceImpl.excluir()");
		repository.delete(idLembrete);

	}

	@Override
	public Lembrete gravar(Lembrete lembrete, Long idPessoa) throws PetShopBusinessException {
		logger.info("LembreteServiceImpl.gravar()");
		lembrete.setDtCadastro(new Date());
		lembrete.setPessoa(pessoaService.findById(idPessoa));
		lembrete.setUsuario(authenticationService.get());
		repository.save(lembrete);
		return lembrete;
	}

	@Override
	public List<Lembrete> findAtivo() throws PetShopBusinessException {
		logger.info("LembreteServiceImpl.findAtivo()");
		return repository.findByStatus(StatusLembreteEnum.ABERTO);
	}

	@Override
	public void fechar(Long idLembrete) throws PetShopBusinessException {
		logger.info("LembreteServiceImpl.fechar()");
		Lembrete obj = repository.findOne(idLembrete);
		obj.setStatus(StatusLembreteEnum.FECHADO);
		repository.save(obj);

	}

	@Override
	public void realizar(Long idLembrete) throws PetShopBusinessException {
		logger.info("LembreteServiceImpl.realizar()");
		Lembrete obj = repository.findOne(idLembrete);
		obj.setStatus(StatusLembreteEnum.REALIZADO);
		repository.save(obj);
	}

}
