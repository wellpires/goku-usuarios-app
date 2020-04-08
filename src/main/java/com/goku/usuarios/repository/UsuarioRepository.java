package com.goku.usuarios.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.goku.usuarios.model.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

	Optional<Usuario> findByLogin(String login);

}
