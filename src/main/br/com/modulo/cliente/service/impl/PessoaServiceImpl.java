package br.com.modulo.cliente.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.compartilhado.execao.PetShopBusinessException;
import br.com.modulo.cliente.entidade.Pessoa;
import br.com.modulo.cliente.repository.PessoaRepository;
import br.com.modulo.cliente.service.PessoaService;

@Service
public class PessoaServiceImpl implements PessoaService {

	private static final Logger logger = LoggerFactory.getLogger(PessoaServiceImpl.class);

	@Autowired
	private PessoaRepository pessoaRepository;

	@Override
	public Iterable<Pessoa> findAll() {
		logger.info("PessoaServiceImpl.findAll()");
		return pessoaRepository.findAll();
	}

	@Override
	public Pessoa gravar(Pessoa pessoa) throws PetShopBusinessException {
		Pessoa pessaoDb = this.pessoaRepository.save(pessoa);
		logger.info("PessoaServiceImpl.gravar(), sucesso:" + pessaoDb);
		return pessaoDb;

	}

	@Override
	public Pessoa findById(Long idPessoa) throws PetShopBusinessException {
		return pessoaRepository.findOne(idPessoa);
	}

}
