package com.example.movedb.MovieDB.controller;


import com.example.movedb.MovieDB.db.MySqlHandler;
import com.example.movedb.MovieDB.model.Movie;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
public class MovieDBController {

    @GetMapping("/getmovie/{movieid}")
    public Movie getMovieByID(
            @PathVariable("movieid") int movieID) throws SQLException {

        /** Get Movie from db */
        MySqlHandler mySqlHandler = new MySqlHandler();
        Movie movie = mySqlHandler.getMovieById(movieID);

        return movie;
    }



}
