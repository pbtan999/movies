package com.pbtan.movies.inventory;

import com.pbtan.movies.exceptions.MovieAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(path = "api/movies")
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

    @PostMapping
    public void addNewMovie(@RequestBody Movie movie) {
        // TODO response 200, JSON SCHEMA
        try {
            movieService.addNewMovie(movie);
        } catch (MovieAlreadyExistsException e) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, e.getMessage()
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