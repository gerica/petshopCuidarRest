package br.com.modulo.venda.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.modulo.venda.entidade.ProdutoCliente;

public interface ProdutoClienteRepository extends CrudRepository<ProdutoCliente, Long> {

	@Modifying
	@Transactional(readOnly = false, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, rollbackFor = {
			Exception.class })
	@Query("delete from ProdutoCliente pc where pc.orcamento.id = ?1")
	void deleteByIdOrcamento(Long id);

}
