package br.com.modulo.cliente.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.compartilhado.execao.PetShopBusinessException;
import br.com.modulo.cliente.entidade.Documento;
import br.com.modulo.cliente.entidade.Pessoa;
import br.com.modulo.cliente.repository.DocumentoRepository;
import br.com.modulo.cliente.service.DocumentoService;
import br.com.modulo.cliente.service.PessoaService;
import br.com.util.ValidadorUtil;

@Service
public class DocumentoServiceImpl implements DocumentoService {

	private static final Logger logger = LoggerFactory.getLogger(DocumentoServiceImpl.class);

	@Autowired
	private PessoaService pessoaService;

	@Autowired
	private DocumentoRepository documentoRepository;

	@Override
	public void excluir(Long idDocumento) throws PetShopBusinessException {
		System.out.println("DocumentoServiceImpl.excluir()");
		documentoRepository.delete(idDocumento);
	}

	@Override
	public List<Documento> findByIdPessoa(Long idPessoa) throws PetShopBusinessException {
		logger.info("DocumentoServiceImpl.findByIdPessoa()");
		return documentoRepository.findByPessoa(idPessoa);
	}

	@Override
	public void gravar(Documento documento, Long idPessoa) throws PetShopBusinessException {
		validarDuplicidade(documento, idPessoa);
		validarCPFOuCNPJ(documento);
		Pessoa pessoa = pessoaService.findById(idPessoa);
		documento.setPessoa(pessoa);
		documentoRepository.save(documento);
		logger.info("DocumentoServiceImpl.gravar()");

	}

	private void validarCPFOuCNPJ(Documento documento) throws PetShopBusinessException {
		if (documento.getTipoDocumento().getDescricao().equals("CPF")) {
			if (!ValidadorUtil.isValidCPF(documento.getNumero())) {
				throw new PetShopBusinessException(
						"O númedo do CPF informado está incorreto, por favor digite novo CPF.");
			}
		} else if (documento.getTipoDocumento().getDescricao().equals("CNPJ")) {
			if (!ValidadorUtil.isValidCNPJ(documento.getNumero())) {
				throw new PetShopBusinessException(
						"O númedo do CNPJ informado está incorreto, por favor digite novo CNPJ.");
			}
		}

	}

	private void validarDuplicidade(Documento documento, Long idPessoa) throws PetShopBusinessException {
		List<Documento> result = documentoRepository.findByTipoDocumentoAndIdPessoa(idPessoa,
				documento.getTipoDocumento().getId());
		if (result.size() > 0 && documento.getId() == null) {
			throw new PetShopBusinessException(
					"O cliente somente pode ter cadastrado um documento por tipo. O tipo de documento informado "
							+ documento.getTipoDocumento().getDescricao() + " já está cadastrado para esse cliente.");

		}

	}

}
