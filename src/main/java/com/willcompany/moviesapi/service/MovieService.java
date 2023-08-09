package com.willcompany.moviesapi.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.willcompany.moviesapi.model.Movie;
import com.willcompany.moviesapi.repository.MovieRepository;

import lombok.Data;

@Data
@Service
public class MovieService {

	@Autowired
	private MovieRepository movieRepository;

	public Optional<Movie> getMovie(final Integer id) {
		return movieRepository.findById(id);
	}

	public Iterable<Movie> getMovies() {
		return movieRepository.findAll();
	}

	public Movie saveMovie(Movie movie) {
		Movie savedMovie = movieRepository.save(movie);
		return savedMovie;
	}

	public void deleteMovie(final Integer id) {
		movieRepository.deleteById(id);
	}

}
