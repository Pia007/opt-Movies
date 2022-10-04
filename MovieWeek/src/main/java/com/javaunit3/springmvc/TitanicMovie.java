package com.javaunit3.springmvc;

import org.springframework.stereotype.Component;

//P1-6 create Titanic movie file that implements the Movie interface and define it as a Spring Component
@Component
public class TitanicMovie implements Movie {

    // P1-6a methods
    @Override
    public String getTitle() {
        return "Titanic";
    }

    @Override
    public String getMaturityRating() {
        return "PG-13";
    }

    @Override
    public String getGenre() {
        return "Romance";
    }
}
