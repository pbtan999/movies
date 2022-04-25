package com.pbtan.movies.exceptions;

import org.springframework.http.HttpStatus;

public class MovieAlreadyExistsException extends CustomException {

    public MovieAlreadyExistsException(String message) {
        super(message);
        httpStatus = HttpStatus.CONFLICT;
    }
}
