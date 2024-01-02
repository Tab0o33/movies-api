package com.willcompany.moviesapi.model;

import com.willcompany.moviesapi.utils.UserMovieId;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@IdClass(UserMovieId.class)
@Table(name = "user_movie")
public class UserMovie {

	@Id
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@Id
	@ManyToOne
	@JoinColumn(name = "movie_id")
	private Movie movie;

	private int rating;

	@JoinColumn(name = "is_watched")
	private Boolean isWatched;

	@JoinColumn(name = "to_watch")
	private Boolean toWatch;

}
