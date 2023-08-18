package com.tranquilmagpie.spring.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.Instant;

@ControllerAdvice // to handle multiple exceptions
public class ApiExceptionHandler {

    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public ResponseEntity<ApiExceptionResponse>  handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        ApiExceptionResponse apiExceptionResponse = new ApiExceptionResponse(
                e.getMessage(),
                httpStatus,
                Instant.now()
        );

        return new ResponseEntity<>(apiExceptionResponse, httpStatus);
    }

    @ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ApiExceptionResponse>  handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        ApiExceptionResponse apiExceptionResponse = new ApiExceptionResponse(
                e.getMessage(),
                httpStatus,
                Instant.now()
        );

        return new ResponseEntity<>(apiExceptionResponse, httpStatus);
    }

    @ExceptionHandler(value = {ApiRequestNotFoundException.class})
    public ResponseEntity<ApiExceptionResponse> handleApiRequestNotFoundException(ApiRequestNotFoundException e) {

        HttpStatus httpStatus = HttpStatus.NOT_FOUND;

        ApiExceptionResponse apiExceptionResponse = new ApiExceptionResponse(
                e.getMessage(),
                httpStatus,
                Instant.now()
        );

        return new ResponseEntity<>(apiExceptionResponse, httpStatus);
    }

    // TODO: check if this handler is overridden by other exceptions/handlers
    @ExceptionHandler(value = {RuntimeException.class})
    public ResponseEntity<String> handleRuntimeException(RuntimeException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
