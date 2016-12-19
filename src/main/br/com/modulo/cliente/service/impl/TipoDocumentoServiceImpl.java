package br.com.modulo.cliente.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.compartilhado.execao.PetShopBusinessException;
import br.com.modulo.cliente.entidade.TipoDocumento;
import br.com.modulo.cliente.repository.TipoDocumentoRepository;
import br.com.modulo.cliente.service.TipoDocumentoService;

@Service
public class TipoDocumentoServiceImpl implements TipoDocumentoService {

	@Autowired
	private TipoDocumentoRepository tipoDocumentoRepository;

	@Override
	public Iterable<TipoDocumento> findAll() throws PetShopBusinessException {
		return tipoDocumentoRepository.findAll();
	}

}
