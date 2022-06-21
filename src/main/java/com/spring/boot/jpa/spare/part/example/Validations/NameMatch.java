package com.spring.boot.jpa.spare.part.example.Validations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = ValidName.class)
@Documented
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RUNTIME)
public @interface NameMatch {
    String message() default "Name is not valid";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
