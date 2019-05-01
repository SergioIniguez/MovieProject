package com.moviesproject.testproject.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

@Data
@AllArgsConstructor
@Entity
public class Movie {

	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
    @JsonProperty(value = "id")
    private Long Id;
    @JsonProperty(value = "title")
    private String title;
    @JsonProperty(value = "year")
    private String year;
    @JsonProperty(value = "genres")
    private String[] genres;
    @JsonProperty(value = "ratings")
    private String[] ratings;
    @JsonProperty(value = "poster")
    private String poster;
    @JsonProperty(value = "contentRating")
    private String contentRating;
    @JsonProperty(value = "duration")
    private String duration;
    @JsonProperty(value = "releaseDate")
    private String releaseDate;
    @JsonProperty(value = "averageRating")
    private String averageRating;
    @JsonProperty(value = "originalTitle")
    private String originalTitle;
    @JsonProperty(value = "storyline")
    @Lob
    private String storyline;
    @JsonProperty(value = "actors")
    private String[] actors;
    @JsonProperty(value = "imdbRating")
    private String imdbRating;
    @JsonProperty(value = "posterurl")
    private String posterUrl;

    public Movie() {}
}    