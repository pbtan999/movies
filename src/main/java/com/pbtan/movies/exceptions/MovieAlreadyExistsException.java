package com.pbtan.movies.exceptions;

import org.springframework.http.HttpStatus;

public class MovieAlreadyExistsException extends MovieException {

    public MovieAlreadyExistsException(String message) {
        super(message);
        httpStatus = HttpStatus.CONFLICT;
    }
}
