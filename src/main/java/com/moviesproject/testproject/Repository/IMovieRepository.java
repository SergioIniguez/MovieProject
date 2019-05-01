package com.moviesproject.testproject.Repository;


import com.moviesproject.testproject.Model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IMovieRepository extends JpaRepository<Movie, Long> {


    public List<Movie> findByYear(String year);

    public List<Movie> findByGenres(String[] genres);
}
