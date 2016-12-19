package br.com.compartilhado.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.compartilhado.entidade.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

	List<Usuario> findByAccountLocked(boolean accountLocked);

	Usuario findByEmail(String email);

	Usuario findByEmailAndPassword(String email, String password);

	List<Usuario> findByEnabled(boolean enabled);

	Usuario findByPassword(String passwordEncode);

	Usuario findByUsername(String username);

}
