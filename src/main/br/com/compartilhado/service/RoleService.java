package br.com.compartilhado.service;

import java.util.List;

import br.com.compartilhado.entidade.permissao.Role;

public interface RoleService {

	List<Role> findAll();

	Role findByNome(String nome);

}
