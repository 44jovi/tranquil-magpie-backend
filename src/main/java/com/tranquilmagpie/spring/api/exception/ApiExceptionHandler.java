package com.tranquilmagpie.spring.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.Instant;

@ControllerAdvice // to handle multiple exceptions
public class ApiExceptionHandler {

    @ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
    public ResponseEntity<String>  handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        String message = e.getMessage();

        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

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
