package com.libraryproject.service;

import com.libraryproject.domain.Book;
import com.libraryproject.domain.BookCopy;
import com.libraryproject.domain.dto.BookCopyDto;
import com.libraryproject.domain.dto.BookDto;
import com.libraryproject.domain.repository.BookCopyRepository;
import com.libraryproject.domain.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookDbService {
    private final BookRepository bookRepository;
    private final BookCopyRepository bookCopyRepository;

    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }
    public Book getBook(Long bookId){
        return bookRepository.findById(bookId).orElse(null);
    }
    public List<BookCopy> getBookCopies(Long bookId){
        return bookCopyRepository.findBookCopiesByBookId(bookId);
    }
}
