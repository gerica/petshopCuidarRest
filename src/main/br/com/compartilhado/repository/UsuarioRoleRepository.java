package br.com.compartilhado.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.compartilhado.entidade.permissao.UsuarioRole;

public interface UsuarioRoleRepository extends CrudRepository<UsuarioRole, Long> {

}
