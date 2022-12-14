package com.javaunit3.springmvc;

import com.javaunit3.springmvc.model.MovieEntity;
import com.javaunit3.springmvc.model.VoteEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Controller
public class MovieController {

    @Autowired
    private BestMovieService bestMovieService;

    // P3-5b
    @Autowired
    private SessionFactory sessionFactory;

    @RequestMapping("/")
    public String getIndexPage() {
        return "index";
    }

    @RequestMapping("/bestMovie")
    public String getBestMoviePage(Model model) {

        Session session = sessionFactory.getCurrentSession();

        session.beginTransaction();

        List<MovieEntity> movieEntityList = session.createQuery("from MovieEntity").list();

        movieEntityList.sort(Comparator.comparing(movieEntity -> movieEntity.getVotes().size()));

        MovieEntity movieWithMostVotes = movieEntityList.get(movieEntityList.size() - 1);
        List<String> voterNames = new ArrayList<>();

        for (VoteEntity vote: movieWithMostVotes.getVotes()) {
            voterNames.add(vote.getVoterName());
        }

        String voterNamesList = String.join(",", voterNames);

        model.addAttribute("bestMovie", movieWithMostVotes.getTitle());
        model.addAttribute("bestMovieVoters", voterNamesList);

        session.getTransaction().commit();

        return "bestMovie";
    }


    @RequestMapping("/voteForBestMovieForm")
    // P4-1a
    public String voteForBestMovieFormPage(Model model) {

        Session session = sessionFactory.getCurrentSession();

        session.beginTransaction();

        List<MovieEntity> movieEntityList = session.createQuery("from MovieEntity").list();

        session.getTransaction().commit();

        model.addAttribute("movies", movieEntityList);

        return "voteForBestMovie";
    }

    @RequestMapping("/voteForBestMovie")
    public String voteForBestMovie(HttpServletRequest request, Model model) {

        // assign requested movie id
        String movieId = request.getParameter("movieId");

        // assign requested voter name
        String voterName = request.getParameter("voterName");

        // start a session
        Session session = sessionFactory.getCurrentSession();

        // begin transaction
        session.beginTransaction();

        // use the movie id to get the movie
        MovieEntity movieEntity = (MovieEntity) session.get(MovieEntity.class, Integer.parseInt(movieId));

        // define a vote obj
        VoteEntity newVote = new VoteEntity();

        // get the voters name
        newVote.setVoterName(voterName);

        // add the vote to the movie
        movieEntity.addVote(newVote);

        // save the movie
        session.update(movieEntity);

        // send it to the db
        session.getTransaction().commit();

        return "voteForBestMovie";
    }

    // P3-5c
    @RequestMapping("/addMovieForm")
    public String addMovieForm() {
        return "addMovie";
    }

    // P3-5d
    @RequestMapping("/addMovie")
    public String addMovie(HttpServletRequest request) {

        String movieTitle = request.getParameter("movieTitle");
        String maturityRating = request.getParameter("maturityRating");
        String genre = request.getParameter("genre");

        // P3-5d - MovieEntity and set values
        MovieEntity movieEntity = new MovieEntity();

        movieEntity.setTitle(movieTitle);
        movieEntity.setMaturityRating(maturityRating);
        movieEntity.setGenre(genre);

        // P3-5e - get a session obj, start a transaction, save MovieEntity, commit transaction
        Session session = sessionFactory.getCurrentSession();

        session.beginTransaction();

        session.save(movieEntity);

        session.getTransaction().commit();

        return "addMovie";
    }
}

/*
Part 2 - 2 - create MovieController and annotate as controller
    a: create getIndexPage() with a request mapping of "/".
       - return ???index??? so that index.html is displayed when someone loads the page with the path ???/???.
P2-3 -  new method getBestMoviePage() with a Spring Model parameter. Set the request mapping to ???/bestMovie???
    a: add an attribute to the model named ???BestMovie???, and use the bestMovieService object to set it to the best movie???s title.
    b: return the String ???bestMovie???, which is the name of the corresponding html page we???ll create.
P2-5d - create a new method with the request mapping of ???voteForBestMovieForm???.
      - This method will simply return the String ???voteForBestMovie???,
      - allowing us to load the page http://localhost:8080/voteForBestMovie when we run our server.
P2-5e - create a new method with the request mapping of ???/voteForBestMovie???.
     - This method will handle the form data submitted by users.
     - Get the submitted movie title from the request, and add it to the model.
     - Return ???voteForBestMovie??? so that view is loaded when the form is submitted.
P3-5b - use field injection to set a private SessionFactory variable - set by the bean defined for SessionFactory
P3-5c - create a method, addMovieForm, with the requestMapping "/addMovieForm"
      - return "addMovie" to direct to the addMovie.html view
P3-5d - create a method addMovie with the request mapping ???addMovie???.
      - get the movie title, maturity rating, and genre from the request and assign them to local variables
P3-5e - create a new MovieEntity object and set all the values
P3-5d - use the session factory, previously injected, to get a session obj
      - begin a transaction
      - save the MovieEntity object
      - commit the transaction
P3 - End: Run your service, test addMovieForm, confirm in your db

P4 - Movie Voting
P4 - 1: Create a dropdown menu for movie voting
P4 - 1a: MovieController - voteForBestMovieFormPage() to get a list of all of the movie entities in the database,
        - populate a ???movies??? attribute in the model with the list.
Part 4 - 3: Saving Votes - modify voteForBestMovie()
    a: get voter name and movie id from request
    b: use movie id to get the existing movie from the db
    c: add a new vote to the movie
    d: save the changes
P4 - 4 Find the best movie, with data
    a: modify the getBestMoviePage() method in the MovieController to get the movie with the most votes from the database.
    b: populate attributes in the model for the best movie title and voters.
    c: display the best movie title and the names of who voted for it on the bestMovie.html
    d: RUN WEB PAGE - add movies, vote, see best movie
*/
