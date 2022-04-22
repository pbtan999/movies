package com.pbtan.movies.checkout;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.pbtan.movies.exceptions.MovieDoesNotExistException;
import com.pbtan.movies.inventory.Movie;
import com.pbtan.movies.inventory.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;

public class InvoiceSerializer extends JsonSerializer<Invoice> {

    private final MovieRepository movieRepository;

    @Autowired
    public InvoiceSerializer(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public void serialize(Invoice invoice, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        for (Map.Entry<Long, BigDecimal> entry: invoice.getFields().entrySet()) {
            Long movieId = entry.getKey();
            Movie movie = movieRepository.findById(movieId)
                            .orElseThrow(
                                    () -> new MovieDoesNotExistException("Movie with id " + movieId + " doesn't exist in database")
                            );
            jsonGenerator.writeObjectField(
                    movie.getTitle(),
                    entry.getValue());
        }
        jsonGenerator.writeEndObject();
    }
}
