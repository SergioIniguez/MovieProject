package com.moviesproject.testproject.Services;

import com.moviesproject.testproject.Model.Movie;
import com.moviesproject.testproject.Repository.IMovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    @Autowired
    private IMovieRepository iMovieRepository;

    //Obtain all the movies
	public ResponseEntity<List<Movie>> getAllMovies() {
    	List<Movie> movies = iMovieRepository.findAll();
    	
        if (movies.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
 
        return new ResponseEntity<>(movies, HttpStatus.OK);
    }

    //Obtain an specific movie
   public ResponseEntity<Optional<Movie>> getMoviesById(Long id) {
    	Optional<Movie> movie = iMovieRepository.findById(id);

    	if (movie == null)
    		return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    	return ResponseEntity.status(HttpStatus.OK).body(movie);
    }

    //Add a movie
    public ResponseEntity<Movie> addMovie(Movie movie) {
    	iMovieRepository.save(movie);
    	
    	return new ResponseEntity<>(HttpStatus.CREATED);
    }

    //Save movies into DB
    public void saveMovies(List<Movie> movies) {
    	iMovieRepository.saveAll(movies);
    }
    	
    
}
