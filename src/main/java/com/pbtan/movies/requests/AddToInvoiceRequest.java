package com.pbtan.movies.requests;

public class AddToInvoiceRequest {

    private Long movieId;
    private int weeks;

    public Long getMovieId() {
        return movieId;
    }
    public int getWeeks() {
        return weeks;
    }
}
