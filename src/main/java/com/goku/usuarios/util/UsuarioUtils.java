package com.goku.usuarios.util;

import java.util.Objects;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UsuarioUtils {

	public static String hashPassword(String senha) {

		Objects.requireNonNull(senha);

		return new BCryptPasswordEncoder().encode(senha);

	}

}
