package com.pbtan.movies.exceptions;

import org.springframework.http.HttpStatus;

public class IncorrectDateException extends CustomException {

    public IncorrectDateException(String message) {
        super(message);
        httpStatus = HttpStatus.BAD_REQUEST;
    }
}
