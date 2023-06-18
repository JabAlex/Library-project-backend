package com.libraryproject.domain.repository;

import com.libraryproject.domain.Movie;
import org.springframework.data.repository.CrudRepository;

public interface MovieRepository extends CrudRepository<Movie, Long> {
}
