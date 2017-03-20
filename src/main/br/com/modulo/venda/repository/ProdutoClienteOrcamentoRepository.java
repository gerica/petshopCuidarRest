package br.com.modulo.venda.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.modulo.venda.entidade.Orcamento;
import br.com.modulo.venda.entidade.ProdutoClienteOrcamento;

public interface ProdutoClienteOrcamentoRepository extends CrudRepository<ProdutoClienteOrcamento, Long> {

	@Modifying
	@Transactional(readOnly = false, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, rollbackFor = {
			Exception.class })
	@Query("delete from ProdutoClienteOrcamento pc where pc.orcamento.id = ?1")
	void deleteByIdOrcamento(Long id);

	List<ProdutoClienteOrcamento> findByOrcamento(Orcamento orcamento);

}
