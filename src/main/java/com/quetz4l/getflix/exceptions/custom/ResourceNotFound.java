package com.quetz4l.getflix.exceptions.custom;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Resource not found")
public class ResourceNotFound extends Exception {
    public ResourceNotFound(String message) {
        super(message);
    }
}
