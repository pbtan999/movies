package com.pbtan.movies.exceptions;

import org.springframework.http.HttpStatus;

public class MovieDoesNotExistException extends CustomException {

    public MovieDoesNotExistException(String message) {
        super(message);
        httpStatus = HttpStatus.NOT_FOUND;
    }

}
