package com.pbtan.movies.exceptions;

import org.springframework.http.HttpStatus;

public class MovieCategoryIsEmptyException extends MovieException {

    public MovieCategoryIsEmptyException(String message) {
        super(message);
        this.httpStatus = HttpStatus.CONFLICT;
    }
}
