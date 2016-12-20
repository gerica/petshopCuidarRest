package br.com.modulo.pet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.com.modulo.pet.entidade.Raca;

public interface RacaRepository extends CrudRepository<Raca, Integer> {

	@Query(value = "SELECT r FROM Raca r LEFT JOIN FETCH r.tipoPet where r.tipoPet.id = ?1")
	List<Raca> findAllByTipoPet(Long idTipoPet);

}
