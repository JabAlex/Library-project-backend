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

    public void addBook(Book book){
        bookRepository.save(book);
    }
    public void addBookCopy(Long bookId){
        Book book = bookRepository.findById(bookId).orElse(null);
        BookCopy bookCopy = new BookCopy(null,
                book,
                "Available");
        bookCopyRepository.save(bookCopy);
    }
    public void rentBookCopy(Long bookCopyId){
        BookCopy bookCopy = bookCopyRepository.findById(bookCopyId).orElse(null);
        bookCopy.setStatus("Rented");
        bookCopyRepository.save(bookCopy);
    }
    public void deleteBook(Long bookId){
        bookRepository.deleteById(bookId);
    }
    public void deleteBookCopy(Long bookCopyId){
        bookCopyRepository.deleteById(bookCopyId);
    }
}
