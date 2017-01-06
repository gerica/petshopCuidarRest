package br.com.modulo.pet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.com.modulo.pet.entidade.Pet;

public interface PetRepository extends CrudRepository<Pet, Long> {

	// @Query(value = "SELECT p FROM Pet p WHERE UPPER(p.dsNome) like
	// UPPER('%'||?1||'%')")
	// List<Pet> findPetByDsNome(String valor);
	List<Pet> findByNomeContainingIgnoreCase(String valor);

	@Query(value = "SELECT p FROM Pet p WHERE p.pessoa.id= ?1")
	List<Pet> findPetByIdPessoa(Long idCliente);

}
