package com.pbtan.movies.exceptions;

import org.springframework.http.HttpStatus;

public class InvoiceDoesNotExistException extends CustomException {

    public InvoiceDoesNotExistException(String message) {
        super(message);
        httpStatus = HttpStatus.NOT_FOUND;
    }
}
