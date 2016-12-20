package br.com.modulo.cliente.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.compartilhado.execao.PetShopBusinessException;
import br.com.modulo.cliente.entidade.Pessoa;
import br.com.modulo.cliente.repository.PessoaRepository;
import br.com.modulo.cliente.service.PessoaService;
import br.com.util.UtilsEmpty;
import ch.qos.logback.core.joran.conditional.IfAction;

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
	public Pessoa findById(Long idPessoa) throws PetShopBusinessException {
		return pessoaRepository.findOne(idPessoa);
	}

	@Override
	public List<Pessoa> findByName(String nomePessoa) throws PetShopBusinessException {
		logger.info("PessoaServiceImpl.findByName()");
		return pessoaRepository.findByNomeContainingIgnoreCase(nomePessoa);
	}

	@Override
	public Pessoa gravar(Pessoa pessoa) throws PetShopBusinessException {
		validar(pessoa);
		Pessoa pessaoDb = this.pessoaRepository.save(pessoa);
		logger.info("PessoaServiceImpl.gravar(), sucesso:" + pessaoDb);
		return pessaoDb;

	}

	private void validar(Pessoa pessoa) throws PetShopBusinessException {
		StringBuilder sb = new StringBuilder();
		if (UtilsEmpty.isEmpty(pessoa)) {
			sb.append("Informe os dados da pessoa para poder gravar.");
		} else {
			if (UtilsEmpty.isEmpty(pessoa.getNome())) {
				sb.append("Informe o nome da pessoa para poder gravar.");
			}
			if (UtilsEmpty.isEmpty(pessoa.getTipoPessoa())) {
				sb.append("Informe o tipo de pesoa para poder gravar.");
			}
			if (UtilsEmpty.isEmpty(pessoa.getDtNascimento())) {
				sb.append("Informe a data de nascimento da pessoa para poder gravar.");
			}
			if ((!UtilsEmpty.isEmpty(pessoa.getTipoPessoa()) && pessoa.getTipoPessoa().equals("FÍSICA"))
					&& UtilsEmpty.isEmpty(pessoa.getSexo())) {
				sb.append("Informe o sexo da pessoa para poder gravar.");
			}

		}

		if (sb.length() > 0) {
			throw new PetShopBusinessException(sb.toString());
		}
	}

}
