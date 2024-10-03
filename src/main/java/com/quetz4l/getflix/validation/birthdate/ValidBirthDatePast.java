package com.quetz4l.getflix.validation.birthdate;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = BirthDatePastValidator.class)
public @interface ValidBirthDatePast {
    String message() default "Birth date must be in the past and equal or greater than 1900-01-01";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}