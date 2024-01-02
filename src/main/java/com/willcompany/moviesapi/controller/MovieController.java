package com.willcompany.moviesapi.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.willcompany.moviesapi.model.Movie;
import com.willcompany.moviesapi.service.MovieService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/public")
public class MovieController {

	@Autowired
	private MovieService movieService;

	/**
	 * Read - Get all movies
	 * 
	 * @return - An Iterable object of Movie full filled
	 */
	@GetMapping("/movies")
	public Iterable<Movie> getMovies() {
		return movieService.getMovies();
	}

	/**
	 * Read - Get one movie by id
	 * 
	 * @param id - The id of the movie to return
	 * @return - An object Movie full filled
	 */
	@GetMapping("/movie/{id}")
	public Movie getMovie(@PathVariable Integer id) {
		Optional<Movie> movie = movieService.getMovie(id);
		if (movie.isPresent()) {
			return movie.get();
		} else {
			return null;
		}
	}

	@PostMapping("/movie")
	public Movie createMovie(@RequestBody Movie movie) {
		return movieService.saveMovie(movie);
	}

	/**
	 * Update - Update an existing movie
	 * 
	 * @param id    - The id of the movie to update
	 * @param movie - The movie object updated
	 * @return
	 */
	@PutMapping("/movie/{id}")
	public Movie updateMovie(@PathVariable("id") final Integer id, @RequestBody Movie movie) {
		Optional<Movie> e = movieService.getMovie(id);
		if (e.isPresent()) {
			Movie currentMovie = e.get();
			String title = movie.getTitle();
			if (title != null) {
				currentMovie.setTitle(title);
			}
			String imgUrl = movie.getImageUrl();
			if (imgUrl != null) {
				currentMovie.setImageUrl(imgUrl);
				;
			}
			movieService.saveMovie(currentMovie);
			return currentMovie;
		} else {
			return null;
		}
	}

	/**
	 * Delete - Delete an movie
	 * 
	 * @param id - The id of the movie to delete
	 */
	@DeleteMapping("/movie/{id}")
	public void deleteMovie(@PathVariable("id") final Integer id) {
		movieService.deleteMovie(id);
	}

}
