package com.pbtan.movies.exceptions;

import org.springframework.http.HttpStatus;

public class MovieTitleIsUsedException extends CustomException {

    public MovieTitleIsUsedException(String message) {
        super(message);
        httpStatus = HttpStatus.CONFLICT;
    }
}
