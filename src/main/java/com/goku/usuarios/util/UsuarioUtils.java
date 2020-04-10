package com.goku.usuarios.util;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Objects;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.goku.usuarios.exception.HashingPasswordException;

public class UsuarioUtils {

	private static final Logger logger = LoggerFactory.getLogger(UsuarioUtils.class);

	public static byte[] hashPassword(String senha) {

		Objects.requireNonNull(senha);

		byte[] encoded = null;
		try {
			SecureRandom random = new SecureRandom();
			byte[] salt = new byte[16];
			random.nextBytes(salt);

			KeySpec spec = new PBEKeySpec(senha.toCharArray(), salt, 65536, 128);
			SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
			encoded = factory.generateSecret(spec).getEncoded();
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			logger.error(e.getMessage(), e);
			throw new HashingPasswordException();
		}

		return encoded;

	}

}
