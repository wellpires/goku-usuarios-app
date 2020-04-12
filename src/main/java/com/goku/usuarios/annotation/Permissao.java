package com.goku.usuarios.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.goku.usuarios.validator.PermissaoValidator;

@Retention(RUNTIME)
@Target({ FIELD, METHOD })
@Constraint(validatedBy = PermissaoValidator.class)
public @interface Permissao {

	String message();

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
	
}
