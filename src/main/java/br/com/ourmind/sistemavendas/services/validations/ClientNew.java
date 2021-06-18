package br.com.ourmind.sistemavendas.services.validations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import br.com.ourmind.sistemavendas.services.validations.ClientNewValidation;

@Constraint(validatedBy = ClientNewValidation.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface ClientNew {

	String message() default "Erro de validacao";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
