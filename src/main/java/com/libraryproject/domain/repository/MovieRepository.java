package com.libraryproject.domain.repository;

import com.libraryproject.domain.Movie;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MovieRepository extends CrudRepository<Movie, Long> {
    @Override
    List<Movie> findAll();
}
