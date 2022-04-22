package com.pbtan.movies.inventory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pbtan.movies.exceptions.*;
import com.pbtan.movies.requests.UpdateMovieRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(path = "api/inventory")
public class MovieController {

    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public List<Movie> getMovies() {
        return movieService.getMovies();
    }

    @GetMapping(path = "category")
    public List<Movie> getMoviesSortedByCategory() {
        return movieService.getMoviesSortedByCategory();
    }

    @GetMapping(path = "rented")
    public List<Movie> getRentedMovies() {
        return movieService.getRentedMovies();
    }

    @PostMapping
    public void addNewMovie(@RequestBody Movie movie) {
        // TODO response 200, JSON SCHEMA
        try {
            movieService.addNewMovie(movie);
        } catch (MovieAlreadyExistsException e) {
            throw new ResponseStatusException(
                    e.getHttpStatus(), e.getMessage()
            );
        }
    }

    @PutMapping(path = {"{movieId}"})
    public void updateMovie(@PathVariable("movieId") Long movieId,
                            @RequestBody UpdateMovieRequest request) {
        try {
            movieService.updateMovie(movieId,
                    request.getTitle(),
                    request.getReleaseYear(), request.getReleaseMonth(), request.getReleaseDay(),
                    request.getCategory(),
                    request.getActors(),
                    request.getDescription());
        } catch (MovieException e) {
            throw new ResponseStatusException(
                    e.getHttpStatus(), e.getMessage()
            );
        }
    }

    @DeleteMapping(path = "{movieId}")
    public void deleteMovie(@PathVariable Long movieId) {
        try {
            movieService.deleteMovie(movieId);
        } catch (MovieDoesNotExistException e) {
            throw new ResponseStatusException(
                    e.getHttpStatus(), e.getMessage()
            );
        }
    }
}

/*
POST request example
{
  "title": "The wolf of Wall Street",
  "releaseYear":2013, "releaseMonth":12, "releaseDay":17,
  "category": "Biographical crime black comedy",
  "actors": "Leonardo DiCaprio, Jonah Hill, Margot Robbie, Matthew McConaughey",
  "description": "Based on the true story of Jordan Belfort, from his rise to a wealthy stock-broker living the high life to his fall involving crime, corruption and the federal government."
}
 */
