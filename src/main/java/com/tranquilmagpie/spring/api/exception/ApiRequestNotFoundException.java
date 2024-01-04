package com.tranquilmagpie.spring.api.exception;

public class ApiRequestNotFoundException extends RuntimeException {
    public ApiRequestNotFoundException(String message) {
        super(message);
    }

    // REVIEW: consider adding parameter for a throwable
}
