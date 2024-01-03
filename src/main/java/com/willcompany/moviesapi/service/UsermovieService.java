package com.willcompany.moviesapi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.willcompany.moviesapi.model.Movie;
import com.willcompany.moviesapi.model.User;
import com.willcompany.moviesapi.model.UserMovie;
import com.willcompany.moviesapi.model.UserMovieDTO;
import com.willcompany.moviesapi.model.UserMoviePatchDTO;
import com.willcompany.moviesapi.repository.MovieRepository;
import com.willcompany.moviesapi.repository.UserMovieRepository;
import com.willcompany.moviesapi.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UsermovieService {

	@Autowired
	private UserMovieRepository userMovieRepository;
	@Autowired
	private MovieService movieService;
	@Autowired
	private MovieRepository movieRepository;
	@Autowired
	private UserRepository userRepository;

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

	public void updateUserMovieByMovieId(Integer userId, Integer movieId, UserMoviePatchDTO patchDTO) {
		Optional<UserMovie> optionalUserMovie = userMovieRepository.findByMovieIdAndUserId(movieId, userId);
		if (optionalUserMovie.isPresent()) {
			UserMovie existingUserMovie = optionalUserMovie.get();
			if (patchDTO.getRating() != null) {
				existingUserMovie.setRating(patchDTO.getRating());
			}
			if (patchDTO.getToWatch() != null) {
				existingUserMovie.setToWatch(patchDTO.getToWatch());
			}
			if (patchDTO.getIsWatched() != null) {
				existingUserMovie.setIsWatched(patchDTO.getIsWatched());
			}
			userMovieRepository.save(existingUserMovie);
		} else {
			UserMovie newUserMovie = new UserMovie();
			if (patchDTO.getRating() != null) {
				newUserMovie.setRating(patchDTO.getRating());
			}
			if (patchDTO.getToWatch() != null) {
				newUserMovie.setToWatch(patchDTO.getToWatch());
			}
			if (patchDTO.getIsWatched() != null) {
				newUserMovie.setIsWatched(patchDTO.getIsWatched());
			}
			// Associer le UserMovie avec le Movie
			Movie movie = movieRepository.findById(movieId)
					.orElseThrow(() -> new EntityNotFoundException("Movie not found with id: " + movieId));
			newUserMovie.setMovie(movie);
			// Associer le UserMovie avec le User
			User user = userRepository.findById(userId)
					.orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));
			newUserMovie.setUser(user);

			userMovieRepository.save(newUserMovie);
		}
	}

}
