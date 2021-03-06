package com.example.movedb.MovieDB.controller;


import com.example.movedb.MovieDB.db.Hibernate_SQLHandler;
import com.example.movedb.MovieDB.db.JDBC_SQLHandler;
import com.example.movedb.MovieDB.model.Movie;
import com.example.movedb.MovieDB.model.Rating;
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

        Hibernate_SQLHandler hibernate_sqlHandler = new Hibernate_SQLHandler();
        hibernate_sqlHandler.open();
        Movie movie = hibernate_sqlHandler.getMovieById(movieID);
        hibernate_sqlHandler.close();

        List<Integer> ratingIDs = movie.getRatingIDs();
        System.out.println(ratingIDs);
        RatingInfo ratingInfo = new RatingInfo();
        RestTemplate restTemplate = new RestTemplate();

        for (int i = 0; i < ratingIDs.size(); i++)
        {
            Rating rating = restTemplate.getForObject("http://localhost:8082/getrating/" + ratingIDs.get(i), Rating.class);
            System.out.println(rating);
            movie.addRating(rating);
        }

        System.out.println(ratingIDs);

        return movie;
    }

    @GetMapping("/deletemovie/{movieid}")
    public Movie deleteMovieById (
            @PathVariable("movieid") int movieID) throws SQLException {
        Hibernate_SQLHandler hibernate_sqlHandler = new Hibernate_SQLHandler();
        Movie movie = getMovieByID(movieID);


        /** DELETE MRM */
        System.out.println("DELETE MRM");
        JDBC_SQLHandler jdbc_sqlHandler = new JDBC_SQLHandler();
        jdbc_sqlHandler.deletemoviIdFromMRM(movieID);

        /** DELETE RATING */
        System.out.println("DELETE RATING");
        List<Integer> ratingIDs = movie.getRatingIDs();
        RestTemplate restTemplate = new RestTemplate();

        for(int i = 0; i < ratingIDs.size(); i++) {
            Rating ratingDelete = restTemplate.getForObject("http://localhost:8082/deleterating/" + ratingIDs.get(i), Rating.class);

            movie.removeRating(ratingDelete);
        }

        /** DELETE MOVIE */
        System.out.println("01 Delete movie");
        hibernate_sqlHandler.open();
        System.out.println("02 Delete movie");
        movie = hibernate_sqlHandler.deleteMovieById(movieID);
        System.out.println("03 Delete movie");
        hibernate_sqlHandler.close();
        System.out.println("05 Delete movie");


        return movie;
    }

}
