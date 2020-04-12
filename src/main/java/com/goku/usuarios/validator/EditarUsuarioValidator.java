package com.goku.usuarios.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.StringUtils;

import com.goku.usuarios.annotation.EditarUsuario;
import com.goku.usuarios.dto.EditarUsuarioDTO;

public class EditarUsuarioValidator implements ConstraintValidator<EditarUsuario, EditarUsuarioDTO> {

	@Override
	public boolean isValid(EditarUsuarioDTO value, ConstraintValidatorContext context) {
		return StringUtils.isNotBlank(value.getSenha());
	}

}
