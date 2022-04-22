package com.pbtan.movies.checkout;

import com.pbtan.movies.exceptions.MovieDoesNotExistException;
import com.pbtan.movies.inventory.Movie;
import com.pbtan.movies.inventory.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CheckoutService {

    private final Invoice currentInvoice;
    private final MovieRepository movieRepository;

    @Autowired
    public CheckoutService(Invoice currentInvoice, MovieRepository movieRepository) {
        this.currentInvoice = currentInvoice;
        this.movieRepository = movieRepository;
    }

    public Invoice getCurrentInvoice() {
        return currentInvoice;
    }

    // TODO transactional cause changes movie rented weeks property?
    @Transactional
    public void addToInvoice(Long movieId, int weeks) throws MovieDoesNotExistException {
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(
                        () -> new MovieDoesNotExistException("Movie with id " + movieId + " doesn't exist in database")
                );
        // TODO negative weeks
        currentInvoice.rentMovie(movie, weeks);
    }

    public void removeFromInvoice(Long movieId) {
        // TODO nullpointerex
        currentInvoice.getFields().remove(movieId);
        // TODO movie is rented = false
    }

    public void clearInvoice() {
        currentInvoice.getFields().clear();
    }
}
