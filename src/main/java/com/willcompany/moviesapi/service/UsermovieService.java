package com.willcompany.moviesapi.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.willcompany.moviesapi.model.UserMovie;
import com.willcompany.moviesapi.model.UserMovieDTO;
import com.willcompany.moviesapi.repository.UserMovieRepository;

@Service
public class UsermovieService {

	@Autowired
	private UserMovieRepository userMovieRepository;

	public Iterable<UserMovie> getAllUsersMovies() {
		return userMovieRepository.findAll();
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
			UserMovieDTO userMovie = new UserMovieDTO();
			userMovie.setId((Integer) row[0]);
			userMovie.setTitle((String) row[1]);
			userMovie.setImageUrl((String) row[2]);
			userMovie.setDescription((String) row[3]);
			userMovie.setPublicRating((Float) row[4]);
			userMovie.setMyRating((Integer) row[5]);
			userMovie.setIsWatched((Boolean) row[6]);
			userMovie.setToWatch((Boolean) row[7]);
			userMovies.add(userMovie);
		}
		return userMovies;
	}

}
