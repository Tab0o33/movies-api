package com.willcompany.moviesapi.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.willcompany.moviesapi.model.User;
import com.willcompany.moviesapi.service.UserService;

@RestController
@RequestMapping("/api/v1/private")
public class UserController {

	@Autowired
	private UserService userService;

	/**
	 * Read - Get all users
	 * 
	 * @return - An Iterable object of User full filled
	 */
	@GetMapping("/users")
	public Iterable<User> getUsers() {
		return userService.getUsers();
	}

	/**
	 * Read - Get one user by id
	 * 
	 * @param id - The id of the user to return
	 * @return - An object User full filled
	 */
	@GetMapping("/user/{id}")
	public User getUser(@PathVariable Integer id) {
		Optional<User> user = userService.getUser(id);
		if (user.isPresent()) {
			return user.get();
		} else {
			return null;
		}
	}

}
