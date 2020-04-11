package com.goku.usuarios.model;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import com.goku.usuarios.util.UsuarioUtils;

@RedisHash("usuario")
public class Usuario implements Serializable {

	private static final long serialVersionUID = -6530348170702345003L;

	@Id
	private String login;

	private String senha;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = UsuarioUtils.hashPassword(senha);
	}

}
