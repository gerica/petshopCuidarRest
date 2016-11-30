package br.com.compartilhado.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.compartilhado.entidade.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

	List<Usuario> findByAccountLocked(boolean accountLocked);

	Usuario findByEmail(String email);

	Usuario findByPassword(String passwordEncode);

	Usuario findByUsername(String username);

	List<Usuario> findByEnabled(boolean enabled);

}
