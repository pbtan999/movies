package com.pbtan.movies.exceptions;

import org.springframework.http.HttpStatus;

public class MovieTitleIsEmptyException extends CustomException {

    public MovieTitleIsEmptyException(String message) {
        super(message);
        httpStatus = HttpStatus.CONFLICT;
    }
}
