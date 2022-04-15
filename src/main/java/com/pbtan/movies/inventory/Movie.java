package com.pbtan.movies.inventory;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Entity
@Table
public class Movie {
    @Id
    @SequenceGenerator(
            name = "movie_sequence",
            sequenceName = "movie_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "movie_sequence"
    )
    private Long id;
    private String title;
    private LocalDate releaseDate;
    private String category;
    private String actors;
    private String description;
    @Transient
    private PriceClass priceClass;
    private BigDecimal price;


    public Movie(String title,
                 Integer releaseYear,
                 Integer releaseMonth,
                 Integer releaseDay,
                 String category,
                 String actors,
                 String description) {
        this.title = title;
        //System.out.println("DEBUG" + "y" + releaseYear + " d" + releaseDay + " m" + releaseMonth);
        // TODO json schema
        Integer year = releaseYear;
        Integer month = releaseMonth;
        Integer day = releaseDay;
        //this.releaseDate = LocalDate.of(year, month, day);
        this.category = category;
        this.actors = actors;
        this.description = description;
        calculatePrice();
    }

    public Movie() {
    }

    /**
     * Calculates which price category the movie falls into. Adjusts movie price accordingly.
     */
    private void calculatePrice() {
        // TODO null localdate
        //this.releaseDate = LocalDate.of(2011, 10, 3);
        LocalDate currentDate = LocalDate.now();
        long weeksFromReleaseDate = ChronoUnit.WEEKS.between(releaseDate, currentDate);
        if (PriceClass.OLD.getWeeksFromRelease() < weeksFromReleaseDate) {
            this.priceClass = PriceClass.OLD;
        }
        else if (PriceClass.REGULAR.getWeeksFromRelease() < weeksFromReleaseDate) {
            this.priceClass = PriceClass.REGULAR;
        }
        else {
            this.priceClass = PriceClass.NEW;
        }
        this.price = this.priceClass.getPrice();
    }

    public String getTitle() {
        return title;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
        //return LocalDate.of(2011, 10, 3);
    }

    public String getCategory() {
        return category;
    }

    public String getActors() {
        return actors;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        //TODO calculate price each time
        return price;
    }
}
