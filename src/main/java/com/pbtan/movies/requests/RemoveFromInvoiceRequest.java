package com.pbtan.movies.requests;

public class RemoveFromInvoiceRequest {
    private Long movieId;

    public RemoveFromInvoiceRequest(Long movieId) {
        this.movieId = movieId;
    }

    public Long getMovieId() {
        return movieId;
    }
}
