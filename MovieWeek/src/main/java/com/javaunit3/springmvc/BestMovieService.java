package com.javaunit3.springmvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class BestMovieService {

    // @Autowired
    // public BestMovieService(Movie movie) {
    //   this.movie = movie;
    // }

    private Movie movie;

    // P1-6c use a Qualifier annotation and default bean id to inject the Titanic movie obj
    @Autowired
    public BestMovieService(@Qualifier("titanicMovie") Movie movie) {
        this.movie = movie;
    }

    public Movie getBestMovie(){
        return movie;
    }

    // @Autowired
    // public void setMovie(Movie movie) {
    //    this.movie = movie;
    // }
}

/*
P1-4 define it as a Spring component so that it will be available in the Spring application context.
  -- define a private Movie property and use Spring annotations so that it is Autowired.
P1-5a remove autowired for movie field
P1-5b setter: takes movie object as param. Annotate it so that it will be used to inject the movie object
P1-5c remove the setter and create an annotated constructor with Movie as a parameter
*/
