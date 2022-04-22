package com.pbtan.movies.inventory;

import com.pbtan.movies.exceptions.MovieActorsIsEmptyException;
import com.pbtan.movies.exceptions.MovieAlreadyExistsException;
import com.pbtan.movies.exceptions.MovieCategoryIsEmptyException;
import com.pbtan.movies.exceptions.MovieDescriptionIsEmptyException;
import com.pbtan.movies.exceptions.MovieDoesNotExistException;
import com.pbtan.movies.exceptions.MovieTitleIsEmptyException;
import com.pbtan.movies.exceptions.MovieTitleIsUsedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
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

    /**
     * Update movie details.
     *
     * @param movieId existing movie id
     * @param title new title
     * @param releaseYear new release year
     * @param releaseMonth new release month
     * @param releaseDay new release day
     * @param category new category
     * @param actors new description of actors
     * @param description new movie description
     * @throws MovieDoesNotExistException movie with that id does not exist
     * @throws MovieTitleIsUsedException new title is already used
     * @throws MovieTitleIsEmptyException new title is blank
     * @throws MovieActorsIsEmptyException new description of actors is blank
     * @throws MovieDescriptionIsEmptyException new movie description is blank
     */
    @Transactional()
    public void updateMovie(Long movieId,
                            String title,
                            Integer releaseYear, Integer releaseMonth, Integer releaseDay,
                            String category,
                            String actors,
                            String description)
            throws MovieDoesNotExistException,
            MovieTitleIsUsedException,
            MovieTitleIsEmptyException,
            MovieActorsIsEmptyException,
            MovieDescriptionIsEmptyException {

        Movie existingMovie = movieRepository.findById(movieId)
                .orElseThrow(
                        () -> new MovieDoesNotExistException("Movie with id " + movieId + " doesn't exist in database")
                );

        if (title != null) {
            if (title.isBlank()) {
                throw new MovieTitleIsEmptyException("New title can't be empty");
            }
            if (movieRepository.findByTitle(title).isPresent()) {
                throw new MovieTitleIsUsedException("New title is already used");
            }
            existingMovie.setTitle(title);
        }

        // TODO localdate negative or in the future, day > 30
        LocalDate oldDate = existingMovie.getReleaseDate();
        if (releaseYear != null) {
            LocalDate newDate = LocalDate.of(releaseYear,
                    oldDate.getMonth(),
                    oldDate.getDayOfMonth());
            existingMovie.setReleaseDate(newDate);
        }
        if (releaseMonth != null) {
            LocalDate newDate = LocalDate.of(oldDate.getYear(),
                    releaseMonth,
                    oldDate.getDayOfMonth());
            existingMovie.setReleaseDate(newDate);
        }
        if (releaseDay != null) {
            LocalDate newDate = LocalDate.of(oldDate.getYear(),
                    oldDate.getMonth(),
                    releaseDay);
            existingMovie.setReleaseDate(newDate);
        }

        if (category != null) {
            if (category.isBlank()) {
                throw new MovieCategoryIsEmptyException("New category can't be empty");
            }
            existingMovie.setCategory(category);
        }

        if (actors != null) {
            if (actors.isBlank()) {
                throw new MovieActorsIsEmptyException("Description of actors can't be empty");
            }
            existingMovie.setActors(actors);
        }

        if (description != null) {
            if (description.isBlank()) {
                throw new MovieDescriptionIsEmptyException("Movie description can't be empty");
            }
            existingMovie.setDescription(description);
        }
    }

    public void deleteMovie(Long movieId) throws MovieDoesNotExistException {
        boolean exists = movieRepository.existsById(movieId);
        if (!exists) {
            throw new MovieDoesNotExistException("Movie with id " + movieId + " doesn't exist in database");
        }
        movieRepository.deleteById(movieId);
    }

    public List<Movie> getRentedMovies() {
        return movieRepository.findAllByRentedTrue();
    }
}
