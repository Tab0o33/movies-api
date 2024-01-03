package com.willcompany.moviesapi.model;

import lombok.Data;

@Data
public class UserMoviePatchDTO {

	private Integer rating;
	private Boolean isWatched;
	private Boolean toWatch;

}