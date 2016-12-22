package br.com.modulo.produto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.com.modulo.produto.entidade.RacaoLote;

public interface RacaoLoteRepository extends CrudRepository<RacaoLote, Long> {

	@Query("SELECT o FROM RacaoLote	o WHERE o.racao.id =?1")
	List<RacaoLote> findByIdRacao(Long idRacao);

}
