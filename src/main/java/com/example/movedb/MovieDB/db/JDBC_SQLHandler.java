package com.example.movedb.MovieDB.db;

import com.example.movedb.MovieDB.model.Movie;

import java.sql.*;

public class JDBC_SQLHandler {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/movies?serverTimezone=UTC";
    private static final String DB_USER = "root";
    private static final String DB_PWD = "root";


    public Movie getMovieById(int movieId) throws SQLException {
        Movie movie = new Movie(movieId);

        // Create DB Connection
        Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PWD);

        // Prepare SQL Execution
        Statement stmt = conn.createStatement();

        String sqlSelectAll = "SELECT * FROM movies";
        ResultSet rs = stmt.executeQuery(sqlSelectAll);

        // ON-DEMAND: Iterate over the result
        while (rs.next()) {
            // ID Column
            int id = rs.getInt("id");

            if (id == movieId) {
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

    public void deletemoviIdFromMRM(int movieID) throws SQLException {
        // Create DB Connection
        Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PWD);

        // Prepare SQL Execution
        Statement stmt = conn.createStatement();

        String sqlSelectAll = "SELECT * FROM movies_rating_mapping";
        ResultSet rs = stmt.executeQuery(sqlSelectAll);

        // ON-DEMAND: Iterate over the result
        while (rs.next()) {
            // userId Column
            int id = rs.getInt("movieId");

            if (id == movieID) {

                String deleteFrom = "DELETE FROM movies_rating_mapping WHERE movieId = " + movieID;
                int result = stmt.executeUpdate(deleteFrom);
                System.out.println("result = " + result);
                break;
            }
        }

        // Close the ResultSet
        rs.close();
        // Close the Statement
        stmt.close();
        // Close the DB Connection
        conn.close();
    }

    public void deletemoviIdFromUMM(int movieID) throws SQLException {
        // Create DB Connection
        Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PWD);

        // Prepare SQL Execution
        Statement stmt = conn.createStatement();

        String sqlSelectAll = "SELECT * FROM user_movie_mapping";
        ResultSet rs = stmt.executeQuery(sqlSelectAll);

        // ON-DEMAND: Iterate over the result
        while (rs.next()) {
            // userId Column
            int id = rs.getInt("movieid");

            if (id == movieID) {

                String deleteFrom = "DELETE FROM user_movie_mapping WHERE movieId = " + movieID;
                int result = stmt.executeUpdate(deleteFrom);
                System.out.println("result = " + result);
                break;
            }
        }

        // Close the ResultSet
        rs.close();
        // Close the Statement
        stmt.close();
        // Close the DB Connection
        conn.close();

    }
}
