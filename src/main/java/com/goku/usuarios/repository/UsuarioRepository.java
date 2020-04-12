package com.goku.usuarios.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;

import com.goku.usuarios.model.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, String> {

	@Cacheable(value = "listar-usuarios-cache")
	List<Usuario> findAll();

	@Cacheable(value = "usuario-cache")
	Optional<Usuario> findById(String login);

	Optional<Usuario> findByPermissao(String permissao);

}
