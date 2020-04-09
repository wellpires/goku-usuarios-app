package com.goku.usuarios.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.goku.usuarios.model.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, String> {

	List<Usuario> findAll();

}
