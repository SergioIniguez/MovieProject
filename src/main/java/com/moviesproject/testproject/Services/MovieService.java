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
    	boolean movieExist = iMovieRepository.existsById(id);

    	if (movieExist == false)
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

    //Obtain an specific movie by year
    public ResponseEntity<List<Movie>> getMoviesByYear(String year) {
        List<Movie> movie = iMovieRepository.findByYear(year);
        boolean yearExist = iMovieRepository.existsByYear(year);

        if (yearExist == false)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return ResponseEntity.status(HttpStatus.OK).body(movie);
    }

    //Obtain an specific movie by genre
    public ResponseEntity<List<Movie>> getMoviesByGenres(String[] genres) {
        List<Movie> movie = iMovieRepository.findByGenres(genres);
        boolean genreExist = iMovieRepository.existsByGenres(genres);

        if (genreExist == false)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return ResponseEntity.status(HttpStatus.OK).body(movie);
    }

    //Obtain movies by rating
    public ResponseEntity<List<Movie>> orderMoviesByRating(String rating) {
	    if(rating.toLowerCase().equals("asc")) {
            return ResponseEntity.status(HttpStatus.OK).body(iMovieRepository.findAllByOrderByImdbRatingAsc());
        } else if(rating.toLowerCase().equals("desc")) {
            return ResponseEntity.status(HttpStatus.OK).body(iMovieRepository.findAllByOrderByImdbRatingDesc());
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
