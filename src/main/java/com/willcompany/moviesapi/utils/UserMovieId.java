package com.willcompany.moviesapi.utils;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class UserMovieId implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer user;
	private Integer movie;

	public UserMovieId() {
		// Constructeur sans arguments requis par Hibernate
	}

	public UserMovieId(Integer userId, Integer movieId) {
		this.user = userId;
		this.movie = movieId;
	}
}
