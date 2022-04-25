package com.pbtan.movies.checkout;

import com.pbtan.movies.exceptions.IncorrectRentPeriodException;
import com.pbtan.movies.exceptions.InvoiceDoesNotExistException;
import com.pbtan.movies.exceptions.MovieAlreadyRentedException;
import com.pbtan.movies.exceptions.MovieDoesNotExistException;
import com.pbtan.movies.inventory.Movie;
import com.pbtan.movies.inventory.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CheckoutService {

    private final InvoiceRepository invoiceRepository;
    private final MovieRepository movieRepository;

    @Autowired
    public CheckoutService(InvoiceRepository invoiceRepository, MovieRepository movieRepository) {
        this.invoiceRepository = invoiceRepository;
        this.movieRepository = movieRepository;
    }

    public Invoice findInvoiceById(Long id) throws InvoiceDoesNotExistException {
        return invoiceRepository.findById(id)
                .orElseThrow(
                        () -> new InvoiceDoesNotExistException("Invoice with id " + id + " does not exist")
                );
    }

    @Transactional
    public void addToInvoice(Long invoiceId, Long movieId, int weeks) throws InvoiceDoesNotExistException,
            MovieDoesNotExistException, MovieAlreadyRentedException, IncorrectRentPeriodException{
        Invoice invoice = findInvoiceById(invoiceId);
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(
                        () -> new MovieDoesNotExistException("Movie with id " + movieId + " doesn't exist")
                );
        if (weeks <= 0) {
            throw new IncorrectRentPeriodException("Rent period must be greater than 0");
        }
        invoice.rentMovie(movie, weeks);
    }

    public Long createInvoice() {
        Invoice invoice = new Invoice();
        invoiceRepository.save(invoice);
        return invoice.getId();
    }
}
