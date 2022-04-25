package com.pbtan.movies.exceptions;

import org.springframework.http.HttpStatus;

public class MovieCategoryIsEmptyException extends CustomException {

    public MovieCategoryIsEmptyException(String message) {
        super(message);
        this.httpStatus = HttpStatus.CONFLICT;
    }
}
