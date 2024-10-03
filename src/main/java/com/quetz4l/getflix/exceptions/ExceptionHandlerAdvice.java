package com.quetz4l.getflix.exceptions;

import com.quetz4l.getflix.dto.response.ErrorResponse;
import com.quetz4l.getflix.exceptions.custom.DeletionIsImpossible;
import com.quetz4l.getflix.exceptions.custom.NotBooleanType;
import com.quetz4l.getflix.exceptions.custom.ResourceAlreadyExists;
import com.quetz4l.getflix.exceptions.custom.ResourceNotFound;
import com.quetz4l.getflix.exceptions.custom.UnknownException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

@RestControllerAdvice
public class ExceptionHandlerAdvice {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );
        return new ErrorResponse(errors);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        Map<String, String> errors = new HashMap<>();
        String fieldName = ex.getName();
        String errorMessage = String.format("The parameter '%s' must be of type '%s'", fieldName, Objects.requireNonNull(ex.getRequiredType()).getSimpleName());
        errors.put(fieldName, errorMessage);
        return new ErrorResponse(errors);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleConstraintViolationException(ConstraintViolationException ex) {
        Map<String, String> errors = new HashMap<>();

        Set<ConstraintViolation<?>> violations = ex.getConstraintViolations();
        for (ConstraintViolation<?> violation : violations) {
            String fieldName = violation.getPropertyPath().toString();

            if (fieldName.contains("page")) {
                errors.put("page", violation.getMessage());
            } else if (fieldName.contains("size")) {
                errors.put("size", violation.getMessage());
            } else {
                errors.put(fieldName, violation.getMessage());
            }
        }

        return new ErrorResponse(errors);
    }

    @ExceptionHandler(ResourceNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse ResourceNotFound(ResourceNotFound e) {
        return new ErrorResponse(e.getMessage());
    }

    @ExceptionHandler(ResourceAlreadyExists.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse ResourceAlreadyExists(ResourceAlreadyExists e) {
        return new ErrorResponse(e.getMessage());
    }

    @ExceptionHandler(DeletionIsImpossible.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse DeletionIsImpossible(DeletionIsImpossible e) {
        return new ErrorResponse(e.getMessage());
    }

    @ExceptionHandler(UnknownException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse UnknownException(UnknownException e) {
        return new ErrorResponse(e.getMessage());
    }

    @ExceptionHandler(NotBooleanType.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse NotBooleanType(NotBooleanType e) {
        return new ErrorResponse(e.getMessage());
    }


}
