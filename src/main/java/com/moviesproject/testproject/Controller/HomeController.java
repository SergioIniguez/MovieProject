package com.moviesproject.testproject.Controller;


import com.moviesproject.testproject.Model.Movie;
import com.moviesproject.testproject.Services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HomeController {

    @Autowired
    private MovieService movieService ;

    @GetMapping("/movies")
    public ResponseEntity<List<Movie>> getMovies() {
        return movieService.getAllMovies();
    }

    /*@GetMapping("/movies/{id}")
    public ResponseEntity<Movie> getMovie(@PathVariable long id) {
        ResponseEntity<Movie> movie = movieService.getMoviesById(id);

        return movie;
    }*/

}
