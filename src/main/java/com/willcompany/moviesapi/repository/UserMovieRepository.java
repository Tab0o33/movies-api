package com.willcompany.moviesapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.willcompany.moviesapi.model.UserMovie;
import com.willcompany.moviesapi.utils.UserMovieId;

public interface UserMovieRepository extends CrudRepository<UserMovie, UserMovieId> {

	final String allMoviesByUserIdQuery = "SELECT m.id, m.title, m.image_url, m.description, m.public_rating, um.rating, um.is_watched, um.to_watch "
			+ "FROM user_movie um join movie m on m.id=um.movie_id " + "WHERE user_id = :userId";

	final String toWatchMoviesByUserIdQuery = allMoviesByUserIdQuery + " AND um.to_watch = true";

	@Query(value = allMoviesByUserIdQuery, nativeQuery = true)
	List<Object[]> findUserMoviesByUserId(@Param("userId") Integer userId);

	@Query(value = toWatchMoviesByUserIdQuery, nativeQuery = true)
	List<Object[]> findToWatchUserMoviesByUserId(@Param("userId") Integer userId);

}
