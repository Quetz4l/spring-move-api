package com.quetz4l.getflix.exceptions.custom;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Input boolean is wrong. It must be true or false")
public class NotBooleanType extends Exception {
    public NotBooleanType(String message) {
        super(message);
    }
}
