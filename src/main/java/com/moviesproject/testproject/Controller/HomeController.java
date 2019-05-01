package com.moviesproject.testproject.Controller;


import com.moviesproject.testproject.Model.Movie;
import com.moviesproject.testproject.Services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@ResponseBody
public class HomeController {

    @Autowired
    private MovieService movieService ;

    @GetMapping("/movie")
    public ResponseEntity<List<Movie>> getMovies(@RequestParam String order) {
        return movieService.getAllMovies();
    }

    @GetMapping("/movie/{id}")
    public ResponseEntity<Optional<Movie>> getMovie(@PathVariable long id) {
        return movieService.getMoviesById(id);
    }

    @PostMapping("/movie")
    public void createMovie(@RequestBody Movie movie) {
        movieService.addMovie(movie);
    }

    @GetMapping("/movie/year/{year}")
    public ResponseEntity<List<Movie>> getMovieByYear(@PathVariable String year) {
        return movieService.getMoviesByYear(year);
    }

    @GetMapping("/movie/genres/{genres}")
    public ResponseEntity<List<Movie>> getMovieByGenre(@PathVariable String[] genres) {
        return movieService.getMoviesByGenres(genres);
    }

    @GetMapping("/movie/")
    public ResponseEntity<List<Movie>> getMovieByRating(@RequestParam String rating) {
        return movieService.orderMoviesByRating(rating);
    }
}
