package com.javaunit3.springmvc;
/* Part2-2 - create MovieController and annotate as controller
// P2-2a - create getIndexPage() with a request mapping of "/".
    // This method should return “index” so that index.html is displayed when someone loads the page with the path “/”.
// P2-3 -  new method getBestMoviePage() with a Spring Model parameter. Set the request mapping to “/bestMovie”
    // add an attribute to the model named “BestMovie”, and use the bestMovieService object to set it to the best movie’s title.
    // Return the String “bestMovie”, which is the name of the corresponding html page we’ll create.
// P2-5d - create a new method with the request mapping of “voteForBestMovieForm”.
    // This method will simply return the String “voteForBestMovie”,
    // allowing us to load the page http://localhost:8080/voteForBestMovie when we run our server.
// P2-5e - create a new method with the request mapping of “/voteForBestMovie”.
    // This method will handle the form data submitted by users.
    // Get the submitted movie title from the request, and add it to the model.
    // Return “voteForBestMovie” so that view is loaded when the form is submitted.
P3-5b - use field injection to set a private SessionFactory variable - set by the bean defined for SessionFactory
P3-5c - create a method, addMovieForm, with the requestMapping "/addMovieForm"
      - return "addMovie" to direct to the addMovie.html view
P3-5d - create a method addMovie with the request mapping “addMovie”.
      - get the movie title, maturity rating, and genre from the request and assign them to local variables
P3-5e - create a new MovieEntity object and set all of the values
P3-5d - use the session factory, previously injected, to get a session obj
      - begin a transaction
      - save the MovieEntity object
      - commit the transaction
P3 - End: Run your service, test addMovieForm, confirm in your db
*/
import com.javaunit3.springmvc.model.MovieEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

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

        model.addAttribute("BestMovie", bestMovieService.getBestMovie().getTitle());
        return "bestMovie";
    }

    @RequestMapping("/voteForBestMovieForm")
    public String voteForBestMovieFormPage() {

        return "voteForBestMovie";
    }

    @RequestMapping("/voteForBestMovie")
    public String voteForBestMovie(HttpServletRequest request, Model model) {

        String movieTitle = request.getParameter("movieTitle");

        model.addAttribute("BestMovieVote", movieTitle);

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
