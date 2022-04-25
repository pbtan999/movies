package com.pbtan.movies.exceptions;

import org.springframework.http.HttpStatus;

public class MovieAlreadyRentedException extends CustomException {

    public MovieAlreadyRentedException(String message) {
        super(message);
        httpStatus = HttpStatus.CONFLICT;
    }
}
