package br.com.modulo.cliente.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.com.modulo.cliente.entidade.Documento;

public interface DocumentoRepository extends CrudRepository<Documento, Long> {

	@Query("SELECT e FROM Documento e WHERE e.pessoa.id =?1")
	List<Documento> findByPessoa(Long idPessoa);

	@Query("SELECT e FROM Documento e WHERE e.pessoa.id =?1 and e.tipoDocumento.id = ?2")
	List<Documento> findByTipoDocumentoAndIdPessoa(Long idPessoa, Long idTipoDocumento);

}
