package com.pbtan.movies.checkout;

import com.pbtan.movies.exceptions.InvoiceDoesNotExistException;
import com.pbtan.movies.exceptions.MovieDoesNotExistException;
import com.pbtan.movies.requests.AddToInvoiceRequest;
import com.pbtan.movies.requests.RemoveFromInvoiceRequest;
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

    @GetMapping(path = "{invoiceId}")
    public Invoice getInvoice(@PathVariable Long invoiceId) {
        return checkoutService.findInvoiceById(invoiceId);
    }

    @PostMapping(path = "{invoiceId}")
    public void addToInvoice(@PathVariable Long invoiceId,
                             @RequestBody AddToInvoiceRequest request) {
        // TODO request validation
        try {
            checkoutService.addToInvoice(invoiceId, request.getMovieId(), request.getWeeks());
        } catch (InvoiceDoesNotExistException | MovieDoesNotExistException e) {
            throw new ResponseStatusException(
                    e.getHttpStatus(), e.getMessage()
            );
        }
    }

    /**
     * Create new invoice.
     * @return new invoice id
     */
    @PostMapping(path = "new")
    public Long createInvoice() {
        return checkoutService.createInvoice();
    }
}
