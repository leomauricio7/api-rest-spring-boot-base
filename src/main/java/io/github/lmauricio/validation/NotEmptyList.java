package io.github.lmauricio.validation;

import io.github.lmauricio.validation.constraintValidation.NotEmptyListValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// anotation customizada para validacao dos dados
@Retention(RetentionPolicy.RUNTIME) //  pra ela ser veriica em tempo de execucao
@Target(ElementType.FIELD) // onde podemos colocar essa anotation
@Constraint(validatedBy = NotEmptyListValidator.class) // diz que é uma anotation de validacao e diz qual é classe que vai fazer a validacao
public @interface NotEmptyList {
    // metodos obrigatorios/default para uma anotation de validacao
    String message () default  "A lista não pode ser vazia.";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };

}
