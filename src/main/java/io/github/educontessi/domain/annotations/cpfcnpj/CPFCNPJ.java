package io.github.educontessi.domain.annotations.cpfcnpj;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Retention(RUNTIME)
@Target({ FIELD, METHOD, CONSTRUCTOR, ANNOTATION_TYPE, TYPE_USE })
@Constraint(validatedBy = CPFCNPJValidation.class)
public @interface CPFCNPJ {

	Class<?>[] groups() default {};

	String message() default "CPF / CNPJ inválido";

	Class<? extends Payload>[] payload() default {};

}
