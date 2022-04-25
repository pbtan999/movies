package com.pbtan.movies.exceptions;

import org.springframework.http.HttpStatus;

public class IncorrectRentPeriodException extends CustomException {
    public IncorrectRentPeriodException(String message) {
        super(message);
        httpStatus = HttpStatus.BAD_REQUEST;
    }
}
