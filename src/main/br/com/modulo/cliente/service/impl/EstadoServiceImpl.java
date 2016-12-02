package br.com.modulo.cliente.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.modulo.cliente.entidade.Estado;
import br.com.modulo.cliente.repository.EstadoRepository;
import br.com.modulo.cliente.service.EstadoService;

@Service
public class EstadoServiceImpl implements EstadoService {
	@Autowired
	private EstadoRepository estadoRepository;

	@Override
	public Iterable<Estado> findAll() {
		return estadoRepository.findAll();
	}

}
