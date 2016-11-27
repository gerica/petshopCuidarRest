package br.com.compartilhado.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.compartilhado.entidade.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

	Usuario findByEmail(String email);

	// Usuario findByUsernameAndPassword(String username, String Password);

	Usuario findByUsername(String username);

}
