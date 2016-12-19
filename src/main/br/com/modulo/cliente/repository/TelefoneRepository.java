package br.com.modulo.cliente.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.com.modulo.cliente.entidade.Telefone;

public interface TelefoneRepository extends CrudRepository<Telefone, Long> {
	
	@Query("SELECT e FROM Telefone e WHERE e.pessoa.id =?1")	
	List<Telefone> findByPessoa(Long idPessoa);

}
