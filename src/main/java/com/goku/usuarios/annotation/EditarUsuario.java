package com.goku.usuarios.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.goku.usuarios.validator.EditarUsuarioValidator;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.TYPE })
@Constraint(validatedBy = EditarUsuarioValidator.class)
public @interface EditarUsuario {

	String message();

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
