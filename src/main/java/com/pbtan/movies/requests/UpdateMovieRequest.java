package com.pbtan.movies.requests;

public class UpdateMovieRequest {

    private String title;
    private Integer releaseYear;
    private Integer releaseMonth;
    private Integer releaseDay;
    private String category;
    private String actors;
    private String description;

    public String getTitle() {
        return title;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public Integer getReleaseMonth() {
        return releaseMonth;
    }

    public Integer getReleaseDay() {
        return releaseDay;
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
}
