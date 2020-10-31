package com.example.movedb.MovieDB.db;

import com.example.movedb.MovieDB.model.Movie;

import java.sql.*;

public class MySqlHandler {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/movies?serverTimezone=UTC";
    private static final String DB_USER = "root";
    private static final String DB_PWD = "root";


    public Movie getMovieById (int movieId) throws SQLException {
        Movie movie = new Movie(movieId);

        // Create DB Connection
        Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PWD);

        // Prepare SQL Execution
        Statement stmt = conn.createStatement();

        String sqlSelectAll = "SELECT * FROM movies";
        ResultSet rs = stmt.executeQuery(sqlSelectAll);

        // ON-DEMAND: Iterate over the result
        while(rs.next())
        {
            // ID Column
            int id = rs.getInt("id");

            if (id == movieId)
            {
                // movie_name Column
                movie.setTitle(rs.getString("movie_name"));
                // movie_category Column
                movie.setCategory(rs.getString("movie_category"));
                // movie_description Column
                movie.setDescription(rs.getString("movie_description"));
                //movie_ageLimit Column
                movie.setAgeLimit(rs.getInt("movie_ageLimit"));

                //Exit from re
                break;
            }
        }

        // Close the ResultSet
        rs.close();
        // Close the Statement
        stmt.close();
        // Close the DB Connection
        conn.close();

        return movie;
    }
}
