package br.com.modulo.cliente.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.com.modulo.cliente.entidade.Endereco;

public interface EnderecoRepository extends CrudRepository<Endereco, Long> {

	@Query("SELECT e FROM Endereco e WHERE e.pessoa.id =?1")	
	List<Endereco> findByPessoa(Long idPessoa);

}
