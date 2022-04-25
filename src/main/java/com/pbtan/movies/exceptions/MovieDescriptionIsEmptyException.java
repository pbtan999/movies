package com.pbtan.movies.exceptions;

import org.springframework.http.HttpStatus;

public class MovieDescriptionIsEmptyException extends CustomException {

    public MovieDescriptionIsEmptyException(String message) {
        super(message);
        httpStatus = HttpStatus.CONFLICT;
    }
}
