package com.quetz4l.getflix.validation.birthdate;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;


public class BirthDatePastValidator implements ConstraintValidator<ValidBirthDatePast, String> {
    private final LocalDate OLDEST_ALLOWED_DATE = LocalDate.of(1900, 1, 1);

    @Override
    public boolean isValid(String date, ConstraintValidatorContext context) {
        try {
            LocalDate currentDate = LocalDate.now();
            LocalDate dateToValidate = LocalDate.parse(date);
            return currentDate.isAfter(dateToValidate) && dateToValidate.isAfter(OLDEST_ALLOWED_DATE) || dateToValidate.equals(OLDEST_ALLOWED_DATE);
        } catch (Exception e) {
            return false;
        }
    }
}