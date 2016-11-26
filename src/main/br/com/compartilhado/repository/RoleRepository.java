package br.com.compartilhado.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.compartilhado.entidade.permissao.Role;

public interface RoleRepository extends CrudRepository<Role, Long> {

	Role findByNome(String nome);

}
