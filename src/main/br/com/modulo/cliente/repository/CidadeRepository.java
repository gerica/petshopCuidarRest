package br.com.modulo.cliente.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.modulo.cliente.entidade.Cidade;
import br.com.modulo.cliente.entidade.Estado;

public interface CidadeRepository extends CrudRepository<Cidade, Long> {

	List<Cidade> findByEstado(Estado estado);

}
