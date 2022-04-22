package com.pbtan.movies.checkout;

import com.pbtan.movies.exceptions.MovieDoesNotExistException;
import com.pbtan.movies.requests.AddToInvoiceRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(path = "api/checkout")
public class CheckoutController {

    private final CheckoutService checkoutService;

    @Autowired
    public CheckoutController(CheckoutService checkoutService) {
        this.checkoutService = checkoutService;
    }

    @GetMapping
    public Invoice getCurrentInvoice() {
        // TODO serializer
        return checkoutService.getCurrentInvoice();
    }

    @PostMapping(path = "{movieId}")
    public void addToInvoice(@PathVariable Long movieId,
                             @RequestBody AddToInvoiceRequest request) {
        try {
            // TODO weeks null
            checkoutService.addToInvoice(movieId, request.getWeeks());
        } catch (MovieDoesNotExistException e) {
            throw new ResponseStatusException(
                    e.getHttpStatus(), e.getMessage()
            );
        }
    }

    @DeleteMapping(path = "{movieId}")
    public void removeFromInvoice(@PathVariable Long movieId) {
        checkoutService.removeFromInvoice(movieId);
    }

    @DeleteMapping(path = "clear")
    public void clearInvoice() {
        checkoutService.clearInvoice();
    }

}
