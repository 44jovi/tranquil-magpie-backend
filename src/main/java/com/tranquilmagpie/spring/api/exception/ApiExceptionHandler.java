package com.tranquilmagpie.spring.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

// Handle multiple exceptions
@ControllerAdvice
public class ApiExceptionHandler {
    // TODO: handle different exceptions
    @ExceptionHandler(value = {ApiRequestNotFoundException.class})
    public ResponseEntity<Object> handleApiRequestNotFoundException(ApiRequestNotFoundException e) {

        HttpStatus httpStatus = HttpStatus.NOT_FOUND;

        ApiExceptionResponse apiExceptionResponse = new ApiExceptionResponse(
                e.getMessage(),
                httpStatus,
                Instant.now()
        );

        return new ResponseEntity<>(apiExceptionResponse, httpStatus);
    }
}
