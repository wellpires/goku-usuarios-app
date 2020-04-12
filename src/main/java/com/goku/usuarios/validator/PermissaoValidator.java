package com.goku.usuarios.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.StringUtils;

import com.goku.usuarios.annotation.Permissao;

public class PermissaoValidator implements ConstraintValidator<Permissao, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {

		if (StringUtils.isBlank(value)) {
			return true;
		}

		return com.goku.usuarios.enums.Permissao.exists(value);
	}

}
