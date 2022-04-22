package com.pbtan.movies.inventory;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

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

@JsonDeserialize(using = MovieDeserializer.class)
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
    private BigDecimal price;
    private boolean rented;
    private int weeksRented;


    /**
     * Movie class. Price is calculated automatically.
     *
     * @param title
     * @param releaseYear
     * @param releaseMonth
     * @param releaseDay
     * @param category
     * @param actors
     * @param description
     */
    public Movie(String title,
                 int releaseYear,
                 int releaseMonth,
                 int releaseDay,
                 String category,
                 String actors,
                 String description) {
        this.title = title;
        // TODO json schema
        this.category = category;
        this.actors = actors;
        this.description = description;
        this.releaseDate = LocalDate.of(releaseYear, releaseMonth, releaseDay);
        this.rented = false;
        this.weeksRented = 0;
        calculatePrice(LocalDate.now());
    }

    public Movie() {
    }

    /**
     * Calculates which price category the movie falls into and returns price for a week.
     *
     * If price class changes mid-week, the price is still calculated using previous price class.
     *
     * @param currentDate date on which the price is calculated
     * @return price for a week
     */
    public BigDecimal calculatePrice(LocalDate currentDate) {
        long weeksFromReleaseDate = ChronoUnit.WEEKS.between(releaseDate, currentDate);
        PriceClass priceClass;
        if (PriceClass.OLD.getWeeksFromRelease() < weeksFromReleaseDate) {
            priceClass = PriceClass.OLD;
        }
        else if (PriceClass.REGULAR.getWeeksFromRelease() < weeksFromReleaseDate) {
            priceClass = PriceClass.REGULAR;
        }
        else {
            priceClass = PriceClass.NEW;
        }
        return priceClass.getPrice();
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
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

    /**
     * Get current price of movie (for 1 week).
     * @return current price
     */
    public BigDecimal getPrice() {
        this.price = calculatePrice(LocalDate.now());
        return price;
    }

    public boolean isRented() {
        return rented;
    }

    public void setReleaseDate(LocalDate newDate) {
        releaseDate = newDate;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void rent(int periodInWeeks) {
        // TODO
        if (!this.rented) {
            this.rented = true;
            this.weeksRented += periodInWeeks;
        }
    }
}
