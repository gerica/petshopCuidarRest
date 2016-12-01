package br.com.compartilhado.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.compartilhado.entidade.permissao.UsuarioRole;

public interface UsuarioRoleRepository extends CrudRepository<UsuarioRole, Long> {

	@Modifying
	@Transactional(readOnly = false, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, rollbackFor = {
			Exception.class })
	@Query("delete from UsuarioRole ur where ur.id = ?1")
	void deleteById(Long idUsuario);

}
