package com.spring.boot.jpa.spare.part.example.Validations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidName implements ConstraintValidator<NameMatch,String > {

    String name;

    @Override
    public void initialize(NameMatch constraintAnnotation) {

    }

    @Override
    public boolean isValid(String  string, ConstraintValidatorContext constraintValidatorContext) {

//        return string.matches("^[a-zA-Z]*$");
        return string != null && string.matches("^[a-zA-Z]*$")
                && (string.length() >= 2) && (string.length() < 15);
    }
}
