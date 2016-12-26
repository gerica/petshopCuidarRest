package br.com.modulo.produto.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.modulo.produto.entidade.Medicamento;

public interface MedicamentoRepository extends CrudRepository<Medicamento, Long> {

}
