package br.com.modulo.cliente.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.compartilhado.execao.PetShopBusinessException;
import br.com.modulo.cliente.entidade.Pessoa;
import br.com.modulo.cliente.entidade.Telefone;
import br.com.modulo.cliente.repository.TelefoneRepository;
import br.com.modulo.cliente.service.PessoaService;
import br.com.modulo.cliente.service.TelefoneService;

@Service
public class TelefoneServiceImpl implements TelefoneService {

	private static final Logger logger = LoggerFactory.getLogger(TelefoneServiceImpl.class);

	@Autowired
	private PessoaService pessoaService;

	@Autowired
	private TelefoneRepository telefoneRepository;

	@Override
	public void excluir(Long idTelefone) throws PetShopBusinessException {
		logger.info("TelefoneServiceImpl.excluir()");
		telefoneRepository.delete(idTelefone);

	}

	@Override
	public List<Telefone> findByIdPessoa(Long idPessoa) throws PetShopBusinessException {
		logger.info("TelefoneServiceImpl.findByIdPessoa()");
		return telefoneRepository.findByPessoa(idPessoa);
	}

	@Override
	public void gravar(Telefone telefone, Long idPessoa) throws PetShopBusinessException {
		Pessoa pessoa = pessoaService.findById(idPessoa);
		telefone.setPessoa(pessoa);
		telefoneRepository.save(telefone);
		logger.info("TelefoneServiceImpl.gravar()");

	}

}
