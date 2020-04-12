package com.goku.usuarios.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UsuarioUtils {

	public static String hashPassword(String senha) {
		return new BCryptPasswordEncoder().encode(senha);
	}

}
