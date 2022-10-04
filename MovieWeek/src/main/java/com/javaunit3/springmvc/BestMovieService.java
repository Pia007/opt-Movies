package com.javaunit3.springmvc;

// P1-4 Define it as a Spring component so that it will be available in the Spring application context.

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class BestMovieService {

    // P1-5c remove the setter and create an annotated constructor with Movie as a parameter
    // @Autowired
    // public BestMovieService(Movie movie) {
    //   this.movie = movie;
    // }

    // Define a private Movie property and use Spring annotations so that it is Autowired.
    // P1-5a remove autowired for movie field
    private Movie movie;

    // P1-6c use a Qualifier annotation and default bean id to inject the Titanic movie obj
    @Autowired
    public BestMovieService(@Qualifier("titanicMovie") Movie movie) {
        this.movie = movie;
    }

    public Movie getBestMovie(){
        return movie;
    }

    // P1-5b setter: takes movie object as param. Annotate it so that it will be used to inject the movie object
    // @Autowired
    // public void setMovie(Movie movie) {
    //    this.movie = movie;
    // }
}
