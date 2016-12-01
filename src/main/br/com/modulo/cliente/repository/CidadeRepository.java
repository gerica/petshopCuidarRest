package br.com.modulo.cliente.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.modulo.cliente.entidade.Cidade;
import br.com.modulo.cliente.entidade.Estado;

public interface CidadeRepository extends CrudRepository<Cidade, Long> {

	Cidade findByEstado(Estado estado);

}
