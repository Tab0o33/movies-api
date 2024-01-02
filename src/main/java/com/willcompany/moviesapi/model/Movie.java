package com.willcompany.moviesapi.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
public class Movie {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String title;
	private String imageUrl;
	private String description;
	private Float publicRating;

	@OneToMany // (mappedBy = "movie")
	private List<UserMovie> userMovies = new ArrayList<>();

}