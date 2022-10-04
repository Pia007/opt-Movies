package com.javaunit3.springmvc;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

// P1-1: Create MovieApp class

// P1-4a use annotation so prj pkg is scanned for spring components
@ComponentScan
public class MovieApp {
    public static void main(String[] args) {
        System.out.println("Testing the app!");

        // P1-4b get the application context
        AnnotationConfigApplicationContext applicationsContext = new AnnotationConfigApplicationContext(MovieApp.class);

        // P1-4c: get best movie service using default bean id
        BestMovieService bestMovieService = applicationsContext.getBean("bestMovieService", BestMovieService.class);

        // P1-4d: use the best movie service to get the best movie
        Movie bestMovie = bestMovieService.getBestMovie();

        // P1-4e: print title, maturity rating, and genre of the best movie
        System.out.println("Best movie title: " + bestMovie.getTitle());
        System.out.println("Best movie rating: " + bestMovie.getMaturityRating());
        System.out.println("Best movie genre: " + bestMovie.getGenre());
    }
}
