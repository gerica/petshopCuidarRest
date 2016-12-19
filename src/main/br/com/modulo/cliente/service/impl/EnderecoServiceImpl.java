package br.com.modulo.cliente.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.compartilhado.execao.PetShopBusinessException;
import br.com.modulo.cliente.entidade.Endereco;
import br.com.modulo.cliente.entidade.Pessoa;
import br.com.modulo.cliente.repository.EnderecoRepository;
import br.com.modulo.cliente.service.EnderecoService;
import br.com.modulo.cliente.service.PessoaService;

@Service
public class EnderecoServiceImpl implements EnderecoService {

	private static final Logger logger = LoggerFactory.getLogger(EnderecoServiceImpl.class);

	@Autowired
	private PessoaService pessoaService;

	@Autowired
	private EnderecoRepository enderecoRepository;

	@Override
	public void excluir(Long idEndereco) throws PetShopBusinessException {
		enderecoRepository.delete(idEndereco);

	}

	@Override
	public List<Endereco> findByIdPessoa(Long idPessoa) throws PetShopBusinessException {
		return enderecoRepository.findByPessoa(idPessoa);
	}

	@Override
	public void gravar(Endereco endereco, Long idPessoa) throws PetShopBusinessException {
		Pessoa pessoa = pessoaService.findById(idPessoa);
		endereco.setPessoa(pessoa);
		enderecoRepository.save(endereco);
		logger.info("EnderecoServiceImpl.gravar()");

	}

}
