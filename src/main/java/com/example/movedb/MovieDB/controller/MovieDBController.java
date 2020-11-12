package com.example.movedb.MovieDB.controller;


import com.example.movedb.MovieDB.db.Hibernate_SQLHandler;
import com.example.movedb.MovieDB.db.JDBC_SQLHandler;
import com.example.movedb.MovieDB.model.Movie;
import com.example.movedb.MovieDB.model.RatingInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.sql.SQLException;
import java.util.List;

@RestController
public class MovieDBController {

    @GetMapping("/getmovie/{movieid}")
    public Movie getMovieByID(
            @PathVariable("movieid") int movieID) throws SQLException {

        /** Get Movie from db */

        /** JDBC */
        /**
        JDBC_SQLHandler jdbc_sqlHandler = new JDBC_SQLHandler();
        Movie movie = jdbc_sqlHandler.getMovieById(movieID);
         */


        Hibernate_SQLHandler hibernate_sqlHandler = new Hibernate_SQLHandler();
        hibernate_sqlHandler.open();
        Movie movie = hibernate_sqlHandler.getMovieById(movieID);
        hibernate_sqlHandler.close();

        List<Integer> ratingIDs = movie.getRatingIDs();
        System.out.println(ratingIDs);

        return movie;
    }

    @GetMapping("/deletemovie/{movieid}")
    public Movie deleteMovieById (
            @PathVariable("movieid") int movieID)
    {
        RestTemplate restTemplate = new RestTemplate();
        RatingInfo ratingDelete = restTemplate.getForObject("http://localhost:8082/deleterating/" + movieID, RatingInfo.class);

        Hibernate_SQLHandler hibernate_sqlHandler = new Hibernate_SQLHandler();
        hibernate_sqlHandler.open();
        Movie movie = hibernate_sqlHandler.deleteMovieById(movieID);
        hibernate_sqlHandler.close();

        return movie;
    }


    @GetMapping("/updatemovie/{movieid}/{cat}")
    public Movie updateMovieById (
            @PathVariable("movieid") int movieID,
            @PathVariable("cat") String cat)
    {
        Hibernate_SQLHandler hibernate_sqlHandler = new Hibernate_SQLHandler();
        hibernate_sqlHandler.open();
        Movie movie = hibernate_sqlHandler.updateMovieById(movieID, cat);
        hibernate_sqlHandler.close();

        return movie;
    }



}
