package com.libraryproject.controller;

import com.libraryproject.domain.Book;
import com.libraryproject.domain.dto.BookCopyDto;
import com.libraryproject.domain.dto.BookDto;
import com.libraryproject.domain.dto.DetailedBookDto;
import com.libraryproject.hapi.client.HapiClient;
import com.libraryproject.mapper.BookCopyMapper;
import com.libraryproject.mapper.BookMapper;
import com.libraryproject.service.BookDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("v1/library/books")
@CrossOrigin("*")
@RequiredArgsConstructor
public class BooksController {

    HapiClient hapiClient;
    BookMapper bookMapper;
    BookCopyMapper bookCopyMapper;
    BookDbService dbService;

    @Autowired
    public BooksController(BookMapper bookMapper, BookDbService dbService, BookCopyMapper bookCopyMapper, HapiClient hapiClient) {
        this.bookMapper = bookMapper;
        this.dbService = dbService;
        this.bookCopyMapper = bookCopyMapper;
        this.hapiClient = hapiClient;
    }

    @GetMapping
    public ResponseEntity<List<BookDto>> getBooks(){
        List<BookDto> books = bookMapper.mapToBookDtoList(dbService.getAllBooks());
        return ResponseEntity.ok(books);
    }
    @GetMapping(value = "{bookId}")
    public ResponseEntity<DetailedBookDto> getBook(@PathVariable Long bookId) throws IOException, InterruptedException {
        BookDto book = bookMapper.mapToBookDto(dbService.getBook(bookId));
        DetailedBookDto detailedBook = DetailedBookDto.builder()
                .id(book.getId())
                .title(book.getTitle())
                .author(book.getAuthor())
                .releaseYear(book.getPublicationYear())
                .numberOfAvailableCopies(book.getNumberOfAvailableCopies())
                .synopsis(hapiClient.getBookDescription(book.getTitle()))
                .build();
        return ResponseEntity.ok(detailedBook);
    }
    @GetMapping("/top-month")
    public ResponseEntity<List<BookDto>> getTopBooks(){
        List<BookDto> topBooks = List.of(new BookDto(1L, "popular book1", "guy", 2002, 3),
                new BookDto(2L, "popular book2", "author", 2012, 4));
        return ResponseEntity.ok(topBooks);
    }

    @GetMapping(value = "/copies/{bookId}")
    public ResponseEntity<List<BookCopyDto>> getListOfCopies(@PathVariable Long bookId){
        List<BookCopyDto> bookCopies = bookCopyMapper.mapToBookCopyDtoList(dbService.getBookCopies(bookId));
        return ResponseEntity.ok(bookCopies);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addBook(@RequestBody BookDto bookDto){
        dbService.saveBook(bookMapper.mapToBook(bookDto));
        return ResponseEntity.ok().build();
    }
    @PostMapping(value = "/copies/add/{bookId}")
    public ResponseEntity<Void> addBookCopy(@PathVariable Long bookId){
        dbService.addBookCopy(bookId);
        return ResponseEntity.ok().build();
    }
    @PutMapping(value = "/copies/rent/{bookCopyId}")
    public ResponseEntity<Void> rentBookCopy(@PathVariable Long bookCopyId){
        dbService.rentBookCopy(bookCopyId);
        return ResponseEntity.ok().build();
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> editBook(@RequestBody BookDto bookDto){
        Book book = dbService.getBook(bookDto.getId());
        book.setAuthor(bookDto.getAuthor());
        book.setTitle(bookDto.getTitle());
        book.setPublicationYear(bookDto.getPublicationYear());
        dbService.saveBook(book);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "{bookId}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long bookId){
        dbService.deleteBook(bookId);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping(value = "/copies/{bookCopyId}")
    public ResponseEntity<Void> deleteBookCopy(@PathVariable Long bookCopyId){
        dbService.deleteBookCopy(bookCopyId);
        return ResponseEntity.ok().build();
    }
}
