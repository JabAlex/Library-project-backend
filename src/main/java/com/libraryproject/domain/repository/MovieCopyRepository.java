package com.libraryproject.domain.repository;

import com.libraryproject.domain.MovieCopy;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MovieCopyRepository extends CrudRepository<MovieCopy, Long> {
    List<MovieCopy> findMovieCopiesById(Long bookId);
}
