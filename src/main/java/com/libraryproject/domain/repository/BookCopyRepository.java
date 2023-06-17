package com.libraryproject.domain.repository;

import com.libraryproject.domain.BookCopy;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookCopyRepository extends CrudRepository<BookCopy, Long> {
    List<BookCopy> findBookCopiesByBookId(Long bookId);
}
