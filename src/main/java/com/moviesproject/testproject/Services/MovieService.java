package com.moviesproject.testproject.Services;

import com.moviesproject.testproject.Model.Movie;
import com.moviesproject.testproject.Repository.IMovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    @Autowired
    private IMovieRepository iMovieRepository;

    //Obtain all the movies and optional order by Asc or Desc
	public ResponseEntity<List<Movie>> getAllMovies(String order) {
    	List<Movie> movies = iMovieRepository.findAll();
    	
        if (movies.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        if(order.toLowerCase().equals("asc")) {
            return ResponseEntity.status(HttpStatus.OK).body(iMovieRepository.findAllByOrderByImdbRatingAsc());
        } else if(order.toLowerCase().equals("desc")) {
            return ResponseEntity.status(HttpStatus.OK).body(iMovieRepository.findAllByOrderByImdbRatingDesc());
        }

        return ResponseEntity.status(HttpStatus.OK).body(movies);
    }

    //Obtain an specific movie
   public ResponseEntity<Optional<Movie>> getMoviesById(Long id) {
    	Optional<Movie> movie = iMovieRepository.findById(id);
    	boolean movieExist = iMovieRepository.existsById(id);

    	if (!movieExist)
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

        if (!yearExist)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return ResponseEntity.status(HttpStatus.OK).body(movie);
    }

    //Obtain an specific movie by genre
    public ResponseEntity<List<Movie>> getMoviesByGenres(String[] genres) {
        List<Movie> movie = iMovieRepository.findByGenres(genres);
        boolean genreExist = iMovieRepository.existsByGenres(genres);

        if (!genreExist)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return ResponseEntity.status(HttpStatus.OK).body(movie);
    }
}
