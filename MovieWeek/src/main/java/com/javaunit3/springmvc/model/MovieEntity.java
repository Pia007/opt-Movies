package com.javaunit3.springmvc.model;

// P3-4: MovieEntity class
    // b: define it as a Hibernate entity that uses the "movies" table
    // c: create a private Integer field id, annotated as primary key(@Id) and generated value
        // name the column corresponding to this field as movie_id
    // d: create fields for title, maturity_rating and genre
        // annotate them with the name of the perspective table columns
    // e: create getters and setters for all private properties
    // f: In the HibernateConfig class, add MovieEntity as an annotated class to the SessionFactory.

import javax.persistence.*;

@Entity
@Table(name = "movies")
public class MovieEntity {

    // fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_id")
    private Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "maturity_rating")
    private String maturityRating;

    @Column(name= "genre")
    private String genre;

    // getters and setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMaturityRating() {
        return maturityRating;
    }

    public void setMaturityRating(String maturityRating) {
        this.maturityRating = maturityRating;
    }

    public String getGenre() { return genre; }

    public void setGenre(String genre) { this.genre = genre; }
}
