package com.libraryproject.domain.repository;

import com.libraryproject.domain.Book;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookRepository extends CrudRepository<Book, Long> {
    @Override
    List<Book> findAll();

}
