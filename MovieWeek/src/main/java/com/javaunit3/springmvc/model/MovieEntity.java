package com.javaunit3.springmvc.model;

import javax.persistence.*;
import java.util.List;

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


    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "movie_id")
    private List<VoteEntity> votes;

    // getters and setters
    public List<VoteEntity> getVotes() {
        return votes;
    }

    public void setVotes(List<VoteEntity> votes) {
        this.votes = votes;
    }

    public void addVote( VoteEntity vote) {
        this.votes.add(vote);
    }

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
/*
P3- 4: MovieEntity class
   - define it as a Hibernate entity that uses the "movies" table
   - create a private Integer field id, annotated as primary key(@Id) and generated value
       - name the column corresponding to this field as movie_id
   - create fields for title, maturity_rating and genre
       - annotate them with the name of the perspective table columns
   - create getters and setters for all private properties
       - In the HibernateConfig class, add MovieEntity as an annotated class to the SessionFactory.
P4 - 1c: Modify MovieEntity to have a private field of type List<VoteEntity>
   - using hibernate annotations,
        - indicate a one to many mapping and join column named “movie_id”.
   - include getters and setters.
   - create an addVote() method that adds a vote to the list.


*/
