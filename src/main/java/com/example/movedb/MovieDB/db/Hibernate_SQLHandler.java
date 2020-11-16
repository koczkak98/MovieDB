package com.example.movedb.MovieDB.db;

import com.example.movedb.MovieDB.model.Movie;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;


import javax.persistence.Query;
import javax.transaction.Transactional;
import java.sql.SQLException;

public class Hibernate_SQLHandler {

    private SessionFactory sessionFactory;

    public void open ()
    {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();

        sessionFactory =
                new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }

    public Movie getMovieById(int movieID)
    {
        Movie movie = new Movie(movieID);

        /** Open Session and Begin Transaction */
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        movie = session.get(Movie.class, movieID);

        /** Execute transaction and Close Session */
        session.getTransaction().commit();
        session.close();

        return movie;
    }

    /** Delete */
    public Movie deleteMovieById (int movieID) throws SQLException {
        /** 00 delete from umm */
        JDBC_SQLHandler jdbc_sqlHandler = new JDBC_SQLHandler();
        jdbc_sqlHandler.deletemoviIdFromUMM(movieID);


        /** 01 db */
        Movie movie = getMovieById(movieID);

        Session session = sessionFactory.openSession();

        /** 02 delete movie */
        session.beginTransaction();
        session.delete(movie);
        session.getTransaction().commit();
        session.close();

        return movie;
    }

    /** Update movie */
    public Movie updateMovieById (int movieID, String cat)
    {
        Movie movie = new Movie(movieID);

        /** Open Session and Begin Transaction */
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();

            movie = session.get(Movie.class, movieID);

            movie.setCategory(cat);

            session.saveOrUpdate(movie);

            /** Execute transaction and Close Session */
            session.getTransaction().commit();
            session.close();
        }
        catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }

        return movie;
    }



    public void close ()
    {
        sessionFactory.close();
    }


}
