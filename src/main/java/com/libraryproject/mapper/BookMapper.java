package com.libraryproject.mapper;

import com.libraryproject.domain.Book;
import com.libraryproject.domain.dto.BookDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookMapper {
    public BookDto mapToBookDto(Book book){
        return new BookDto(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getPublicationYear(),
                book.getBookCopies().size()
        );
    }
    public List<BookDto> mapToBookDtoList(List<Book> bookList){
        return bookList.stream()
                .map(this::mapToBookDto)
                .toList();
    }
    public Book mapToBook(BookDto bookDto){
        return new Book(
                bookDto.getId(),
                bookDto.getTitle(),
                bookDto.getAuthor(),
                bookDto.getReleaseYear(),
                new ArrayList<>()
        );
    }
}
