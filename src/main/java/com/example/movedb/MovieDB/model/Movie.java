package com.example.movedb.MovieDB.model;

import javax.persistence.*;

@Entity
@Table(name = "movies")
public class Movie {


    @Id
    @Column(name = "id")

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer movieID;

    @Column(name = "movie_name")
    private String title;

    @Column(name = "movie_category")
    private String category;

    @Column(name = "movie_description")
    private String description;

    @Column(name = "movie_ageLimit")
    private int ageLimit;

    /**
     * @OneToMany(mappedBy = "ratings")
     * private List<Rating> ratingsOfMovies;
     */


    public Movie() {
        super();
    }

    public Movie(Integer movieID) {
        super();
        this.movieID = movieID;
    }


    public Integer getMovieID() {
        return movieID;
    }

    public void setMovieID(Integer movieID) {
        this.movieID = movieID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAgeLimit() {
        return ageLimit;
    }

    public void setAgeLimit(int ageLimit) {
        this.ageLimit = ageLimit;
    }
}
