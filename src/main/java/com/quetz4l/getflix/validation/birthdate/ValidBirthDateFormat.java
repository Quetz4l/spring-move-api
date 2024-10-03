package com.quetz4l.getflix.validation.birthdate;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = BirthDateFormatValidator.class)
public @interface ValidBirthDateFormat {
    String message() default "Invalid date format, expected YYYY-MM-DD";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}