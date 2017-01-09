package br.com.modulo.relacionamento.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.modulo.relacionamento.entidade.Lembrete;
import br.com.modulo.relacionamento.entidade.enums.StatusLembreteEnum;

public interface LembreteRepository extends CrudRepository<Lembrete, Long> {

	List<Lembrete> findByStatus(StatusLembreteEnum aberto);

}
