package com.willcompany.moviesapi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.willcompany.moviesapi.model.Movie;
import com.willcompany.moviesapi.model.UserMovie;
import com.willcompany.moviesapi.model.UserMovieDTO;
import com.willcompany.moviesapi.repository.UserMovieRepository;

@Service
public class UsermovieService {

	@Autowired
	private UserMovieRepository userMovieRepository;

	@Autowired
	private MovieService movieService;

	public Iterable<UserMovie> getAllUsersMovies() {
		return userMovieRepository.findAll();
	}

	public Optional<UserMovieDTO> getUserMovieDTOByIds(Integer userId, Integer movieId) {
		List<Object[]> rows = userMovieRepository.getUserMovieDTOByIds(userId, movieId);
		if (rows.isEmpty()) {
			Optional<Movie> movie = movieService.getMovie(movieId);
			return movie.isPresent() ? Optional.ofNullable(new UserMovieDTO(movie)) : null;
		}
		List<UserMovieDTO> userMovies = new ArrayList<>();
		for (Object[] row : rows) {
			UserMovieDTO userMovie = new UserMovieDTO(row);
			userMovies.add(userMovie);
		}
		return Optional.ofNullable(userMovies.get(0));
	}

	public Iterable<UserMovieDTO> getUserMoviesDTOByUserId(Integer userId, boolean toWatch) {
		List<Object[]> rows;
		if (toWatch) {
			rows = userMovieRepository.findToWatchUserMoviesByUserId(userId);
		} else {
			rows = userMovieRepository.findUserMoviesByUserId(userId);
		}
		List<UserMovieDTO> userMovies = new ArrayList<>();
		for (Object[] row : rows) {
			UserMovieDTO userMovie = new UserMovieDTO(row);
			userMovies.add(userMovie);
		}
		return userMovies;
	}

}
