package com.example.movedb.MovieDB.controller;


import com.example.movedb.MovieDB.model.Movie;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MovieDBController {

    @GetMapping("/getmovie/{movieid}")
    public Movie getMovieByID(
            @PathVariable("movieid") int movieID) {

        /** Create dummy Object instead of DB */
        Movie movie = new Movie(movieID);

        movie.setTitle("Avangers");
        movie.setCategory("Comics");
        movie.setDescription("Bad boys are kicked by Avangers!!");


        return movie;
    }



}
