package com.pbtan.movies.exceptions;

import org.springframework.http.HttpStatus;

public class MovieDescriptionIsEmptyException extends MovieException {

    public MovieDescriptionIsEmptyException(String message) {
        super(message);
        httpStatus = HttpStatus.CONFLICT;
    }
}
