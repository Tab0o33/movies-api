package com.willcompany.moviesapi.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.willcompany.moviesapi.model.User;

// This will be AUTO IMPLEMENTED by Spring into a Bean called movieRepository
// CRUD refers Create, Read, Update, Delete

public interface UserRepository extends CrudRepository<User, Integer> {

	Optional<User> findByEmail(String email);

}