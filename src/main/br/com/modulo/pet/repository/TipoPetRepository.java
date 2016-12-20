package br.com.modulo.pet.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.modulo.pet.entidade.TipoPet;

public interface TipoPetRepository extends CrudRepository<TipoPet, Integer> {

}