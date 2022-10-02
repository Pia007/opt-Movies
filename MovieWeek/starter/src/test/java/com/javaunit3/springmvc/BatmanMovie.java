package com.javaunit3.springmvc;
import org.springframework.stereotype.Component;

// P1-3
@Component
public class BatmanMovie implements Movie {

    //P1-3a methods
    @Override
    public String getTitle() {
        return "Batman: The Dark Knight";
    }

    @Override
    public String getMaturityRating() {
        return "PG-13";
    }

    @Override
    public String getGenre() {
        return "Action";
    }
}
