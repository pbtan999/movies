package com.pbtan.movies.checkout;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.pbtan.movies.inventory.Movie;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@JsonSerialize(using = InvoiceSerializer.class)
@Entity
@Table
public class Invoice {
    @Id
    @SequenceGenerator(
            name = "invoice_sequence",
            sequenceName = "invoice_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "invoice_sequence"
    )
    @Column(name = "id")
    private Long id;

    @ElementCollection
    @CollectionTable(name = "invoice_movie_mapping",
            joinColumns = {@JoinColumn(name = "invoice_id", referencedColumnName = "id")})
    @MapKeyColumn(name = "movie_id")
    @Column(name = "subtotal")
    private Map<Long, BigDecimal> fields;

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
        BigDecimal finalTotalPrice = totalPrice;
        fields.computeIfPresent(movie.getId(), (k, v) -> v.add(finalTotalPrice));
        fields.computeIfAbsent(movie.getId(), k -> finalTotalPrice);
        movie.rent(periodInWeeks);
    }

    public Map<Long, BigDecimal> getFields() {
        return fields;
    }

    public Long getId() {
        return id;
    }
}
