package com.pbtan.movies.exceptions;

import org.springframework.http.HttpStatus;

public abstract class CustomException extends RuntimeException {

    protected HttpStatus httpStatus;

    public CustomException(String message) {
        super(message);
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
