package com.libraryproject.service;

import com.libraryproject.domain.Book;
import com.libraryproject.domain.dto.BookDto;
import com.libraryproject.domain.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookDbService {
    private final BookRepository repository;

    public List<Book> getAllBooks(){
        return repository.findAll();
    }
}
