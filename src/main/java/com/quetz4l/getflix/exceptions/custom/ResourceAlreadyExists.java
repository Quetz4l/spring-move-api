package com.quetz4l.getflix.exceptions.custom;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Resource already exists")
public class ResourceAlreadyExists extends Exception {
    public ResourceAlreadyExists(String message) {
        super(message);
    }
}

