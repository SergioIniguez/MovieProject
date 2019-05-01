package com.moviesproject.testproject;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.moviesproject.testproject.Model.Movie;
import com.moviesproject.testproject.Services.MovieService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@SpringBootApplication
public class TestProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestProjectApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(MovieService movieService) {
		return args -> {
			// read json and write to db
			ObjectMapper mapper = new ObjectMapper();
			TypeReference<List<Movie>> typeReference = new TypeReference<List<Movie>>(){};
			InputStream inputStream = TypeReference.class.getResourceAsStream("/json/movies-f.json");
			try {
				List<Movie> movies = mapper.readValue(inputStream,typeReference);
				movieService.saveMovies(movies);
				System.out.println("Movies loaded into DB!");
			} catch (IOException e){
				System.out.println("Unable to load movies into DB : " + e.getMessage());
			}
		};
	}

}
