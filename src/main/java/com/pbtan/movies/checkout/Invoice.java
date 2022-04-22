package com.pbtan.movies.checkout;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.pbtan.movies.inventory.Movie;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;

@JsonSerialize(using = InvoiceSerializer.class)
@Component
public class Invoice {
    private HashMap<Long, BigDecimal> fields;

    public Invoice() {
        fields = new HashMap<>();
    }

    public void rentMovie(Movie movie, int periodInWeeks) {
        BigDecimal totalPrice = BigDecimal.ZERO;
        LocalDate currentDate = LocalDate.now();
        for (int i = 1; i <= periodInWeeks; i++) {
            totalPrice = totalPrice.add(
                    movie.calculatePrice(currentDate.plusWeeks(i))
            );
        }
        // TODO compute if absent
        BigDecimal finalTotalPrice = totalPrice;
        fields.computeIfPresent(movie.getId(), (k, v) -> v.add(finalTotalPrice));
        fields.computeIfAbsent(movie.getId(), k -> finalTotalPrice);
        movie.rent(periodInWeeks);
    }

    public HashMap<Long, BigDecimal> getFields() {
        return fields;
    }
}
