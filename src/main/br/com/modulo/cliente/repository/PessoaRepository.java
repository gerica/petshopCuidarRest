package br.com.modulo.cliente.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.modulo.cliente.entidade.Pessoa;

public interface PessoaRepository extends CrudRepository<Pessoa, Long> {

//	@Query("SELECT p FROM Pessoa p WHERE p ilike '%?1%'")
	List<Pessoa> findByNomeContainingIgnoreCase(String nomePessoa);

}
