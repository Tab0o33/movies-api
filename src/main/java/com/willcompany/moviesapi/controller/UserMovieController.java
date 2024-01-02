package com.willcompany.moviesapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.willcompany.moviesapi.model.UserMovie;
import com.willcompany.moviesapi.model.UserMovieDTO;
import com.willcompany.moviesapi.service.UsermovieService;
import com.willcompany.moviesapi.utils.JwtUtils;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1")
public class UserMovieController {

	@Autowired
	private UsermovieService userMovieService;

	@Autowired
	private JwtUtils jwtUtils;

	/**
	 * Read - Get all usermovie
	 * 
	 * @return - An Iterable object of UserMovie full filled
	 */
	@GetMapping("/private/users/movies")
	public Iterable<UserMovie> getAllUsersMovies() {
		return userMovieService.getAllUsersMovies();
	}

	/**
	 * Read - Get all UserMovieDTO for one user
	 * 
	 * @param id      - The id of the user
	 * @param toWatch - Can be used with true value to filter on only movies the
	 *                user wants to watch (optional)
	 * @return - An Iterable object of UserMovieDTO full filled
	 */
	@GetMapping("/authenticated/user/me/movies")
	public Iterable<UserMovieDTO> getUserMoviesDTOByUserId(HttpServletRequest request,
			@RequestParam(name = "toWatch", required = false) boolean toWatch) {
		String jwt = jwtUtils.getJwtFromCookies(request);
		int userId = jwtUtils.getUserIdFromJwtToken(jwt);
		return userMovieService.getUserMoviesDTOByUserId(userId, toWatch);
	}

}
