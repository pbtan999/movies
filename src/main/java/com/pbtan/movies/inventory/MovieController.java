package com.pbtan.movies.inventory;

import com.pbtan.movies.exceptions.MovieAlreadyExistsException;
import com.pbtan.movies.exceptions.MovieDoesNotExistException;
import com.pbtan.movies.exceptions.CustomException;
import com.pbtan.movies.requests.UpdateMovieRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
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

    @GetMapping(path = "popularity")
    public List<Movie> getMoviesSortedByPopularity() {
        return movieService.getMoviesSortedByPopularity();
    }

    @GetMapping(path = "rented")
    public List<Movie> getRentedMovies() {
        return movieService.getRentedMovies();
    }

    /**
     * Add new movie to database.
     * @param movie new movie
     * @return new movie id
     */
    @PostMapping
    public Long addNewMovie(@RequestBody Movie movie) {
        try {
            return movieService.addNewMovie(movie);
        } catch (MovieAlreadyExistsException e) {
            throw new ResponseStatusException(
                    e.getHttpStatus(), e.getMessage()
            );
        }
    }

    @PatchMapping(path = {"{movieId}"})
    public void updateMovie(@PathVariable("movieId") Long movieId,
                            @RequestBody UpdateMovieRequest request) {
        try {
            movieService.updateMovie(movieId,
                    request.getTitle(),
                    request.getReleaseYear(), request.getReleaseMonth(), request.getReleaseDay(),
                    request.getCategory(),
                    request.getActors(),
                    request.getDescription());
        } catch (CustomException e) {
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
