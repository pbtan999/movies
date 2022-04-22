package com.pbtan.movies.exceptions;

import org.springframework.http.HttpStatus;

public class MovieTitleIsUsedException extends MovieException {

    public MovieTitleIsUsedException(String message) {
        super(message);
        httpStatus = HttpStatus.CONFLICT;
    }
}
