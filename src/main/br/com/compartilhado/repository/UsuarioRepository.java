package br.com.compartilhado.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.compartilhado.entidade.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

	Usuario findByUsername(String username);

	// Usuario findByUsernameAndPassword(String username, String Password);

	Usuario findByEmail(String email);

}
