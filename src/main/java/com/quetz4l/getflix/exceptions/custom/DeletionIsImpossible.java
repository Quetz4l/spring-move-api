package com.quetz4l.getflix.exceptions.custom;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Deletion is impossible because it has a relationship")
public class DeletionIsImpossible extends Exception {
    public DeletionIsImpossible(String message) {
        super(message);
    }
}
