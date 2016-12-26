package br.com.modulo.produto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.com.modulo.produto.entidade.MedicamentoLote;

public interface MedicamentoLoteRepository extends CrudRepository<MedicamentoLote, Long> {

	@Query("SELECT o FROM MedicamentoLote o WHERE o.medicamento.id =?1")
	List<MedicamentoLote> findByIdMedicamento(Long idMedicamento);

}
