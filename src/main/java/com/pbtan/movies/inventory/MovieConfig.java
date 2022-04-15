package com.pbtan.movies.inventory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class MovieConfig {
// 2013 5 1
    @Bean
    CommandLineRunner commandLineRunner(MovieRepository movieRepository) {
        return args -> {
            Movie gatsby = new Movie("The Great Gatsby",
                    2022, 3, 1,
                    "Historical romantic drama",
                    "Leonardo DiCaprio, Tobey Maguire, Carey Mulligan, Joel Edgerton, Jason Clarke, Isla Fisher, Elizabeth Debicki",
                    "A writer and wall street trader, Nick, finds himself drawn to the past and lifestyle of his millionaire neighbor, Jay Gatsby.");

            movieRepository.saveAll(List.of(gatsby));
        };
    }
}
