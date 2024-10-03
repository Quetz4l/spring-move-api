package com.quetz4l.getflix.exceptions.custom;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Unknown exception. Please contact the administrator")
public class UnknownException extends Exception {

    public UnknownException(String message) {
        super(message);
    }
}
