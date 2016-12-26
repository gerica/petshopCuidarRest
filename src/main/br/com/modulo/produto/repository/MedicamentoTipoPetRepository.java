package br.com.modulo.produto.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.modulo.produto.entidade.Medicamento;
import br.com.modulo.produto.entidade.MedicamentoTipoPet;

public interface MedicamentoTipoPetRepository extends CrudRepository<MedicamentoTipoPet, Long> {

	List<MedicamentoTipoPet> findByMedicamento(Medicamento medicamento);

}
