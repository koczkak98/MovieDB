package com.example.movedb.MovieDB.db;

import com.example.movedb.MovieDB.model.Movie;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

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

    public Movie deleteMovieById (int movieID)
    {
        Movie movie = new Movie(movieID);

        /** Open Session and Begin Transaction */
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();

            movie = session.get(Movie.class, movieID);

            session.delete(movie);

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
