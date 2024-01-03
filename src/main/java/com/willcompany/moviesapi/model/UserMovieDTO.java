package com.willcompany.moviesapi.model;

import java.util.Optional;

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

	public UserMovieDTO(Optional<Movie> optionalMovie) {
		if (optionalMovie.isPresent()) {
			Movie movie = optionalMovie.get();
			this.id = movie.getId();
			this.title = movie.getTitle();
			this.imageUrl = movie.getImageUrl();
			this.description = movie.getDescription();
			this.publicRating = movie.getPublicRating();
			this.myRating = null;
			this.isWatched = false;
			this.toWatch = false;
		}
	}

	public UserMovieDTO(Object[] row) {
		this.id = (Integer) row[0];
		this.title = (String) row[1];
		this.imageUrl = (String) row[2];
		this.description = (String) row[3];
		this.publicRating = (Float) row[4];
		this.myRating = (Integer) row[5];
		this.isWatched = (Boolean) row[6];
		this.toWatch = (Boolean) row[7];
	}

}
