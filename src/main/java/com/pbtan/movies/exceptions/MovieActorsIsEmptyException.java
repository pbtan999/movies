package com.pbtan.movies.exceptions;

import org.springframework.http.HttpStatus;

public class MovieActorsIsEmptyException extends CustomException {

    public MovieActorsIsEmptyException(String message) {
        super(message);
        httpStatus = HttpStatus.CONFLICT;
    }
}
