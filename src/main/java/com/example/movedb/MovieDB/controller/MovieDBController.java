package com.example.movedb.MovieDB.controller;


import com.example.movedb.MovieDB.db.Hibernate_SQLHandler;
import com.example.movedb.MovieDB.db.JDBC_SQLHandler;
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

        /** JDBC */
        /**
        JDBC_SQLHandler jdbc_sqlHandler = new JDBC_SQLHandler();
        Movie movie = jdbc_sqlHandler.getMovieById(movieID);
         */

        Hibernate_SQLHandler hibernate_sqlHandler = new Hibernate_SQLHandler();
        hibernate_sqlHandler.open();
        Movie movie = hibernate_sqlHandler.getMovieById(movieID);
        hibernate_sqlHandler.close();

        return movie;
    }

    @GetMapping("/deletemovie/{movieid}")
    public Movie deleteMovieById (
            @PathVariable("movieid") int movieID)
    {
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
