package com.libraryproject.domain.repository;

import com.libraryproject.domain.BookCopy;
import org.springframework.data.repository.CrudRepository;

public interface BookCopyRepository extends CrudRepository<BookCopy, Long> {
}
