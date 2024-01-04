package com.tranquilmagpie.spring.api.exception;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;

import java.time.Instant;

public class ApiExceptionResponse {

    public ApiExceptionResponse(
            String message,
            HttpStatus httpStatus,
            Instant timestamp
    ) {
        this.message = message;
        this.httpStatus = httpStatus;
        this.timestamp = timestamp;
    }

    private final String message;
    private final HttpStatus httpStatus;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private final Instant timestamp;

    // Getters required by exception handler(s)
    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

}
