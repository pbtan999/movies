package com.pbtan.movies.inventory;

import com.pbtan.movies.exceptions.MovieAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<Movie> getMovies() {
        return movieRepository.findAll();
    }

    /**
     * Add new movie, movies are considered unique by their title.
     * @param movie to add
     */
    public void addNewMovie(Movie movie) throws MovieAlreadyExistsException {
        if (movieRepository.findByTitle(movie.getTitle())
                .isPresent()) {
            throw new MovieAlreadyExistsException("Movie exists");
        }
        movieRepository.save(movie);
    }

    public List<Movie> getMoviesSortedByCategory() {
        return movieRepository.findByOrderByCategoryAsc();
    }
}
