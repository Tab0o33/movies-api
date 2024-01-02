package com.willcompany.moviesapi.model;

import jakarta.persistence.Id;
import lombok.Data;

@Data
public class UserMovieDTO {

	@Id
	private Integer id;
	private String title;
	private String imageUrl;
	private String description;
	private Float publicRating;
	private Integer myRating;
	private Boolean isWatched;
	private Boolean toWatch;

}
