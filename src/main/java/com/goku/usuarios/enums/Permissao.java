package com.goku.usuarios.enums;

import java.util.Objects;

public enum Permissao {

	MASTER, COMUM;

	public static Permissao parseOf(String permissao) {
		return find(permissao);
	}

	public static boolean exists(String permissao) {
		return Objects.nonNull(find(permissao));
	}

	private static Permissao find(String permissao) {
		for (Permissao permissaoItem : values()) {
			if (permissaoItem.name().equalsIgnoreCase(permissao)) {
				return permissaoItem;
			}
		}
		return null;
	}

}
