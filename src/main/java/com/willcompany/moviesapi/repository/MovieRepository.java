package com.willcompany.moviesapi.repository;

import org.springframework.data.repository.CrudRepository;

import com.willcompany.moviesapi.model.*;

// This will be AUTO IMPLEMENTED by Spring into a Bean called movieRepository
// CRUD refers Create, Read, Update, Delete

public interface MovieRepository extends CrudRepository<Movie, Integer> {

}