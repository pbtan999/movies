package com.pbtan.movies.exceptions;

import org.springframework.http.HttpStatus;

public abstract class MovieException extends RuntimeException {

    protected HttpStatus httpStatus;

    public MovieException(String message) {
        super(message);
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
