package br.com.modulo.venda.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.modulo.venda.entidade.Orcamento;
import br.com.modulo.venda.entidade.enums.StatusOrcamentoEnum;

public interface OrcamentoRepository extends CrudRepository<Orcamento, Long> {

	Long countByStatus(StatusOrcamentoEnum aberto);

	List<Orcamento> findByStatus(StatusOrcamentoEnum aberto);

	// @Modifying
	// @Transactional(readOnly = false, isolation = Isolation.DEFAULT,
	// propagation = Propagation.REQUIRED, rollbackFor = {
	// Exception.class })
	// @Query("delete from Orcamento o where o.id = ?1")
	// void deleteById(Long idOrcamento);

}
