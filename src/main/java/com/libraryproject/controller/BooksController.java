package com.libraryproject.controller;

import com.libraryproject.domain.dto.BookCopyDto;
import com.libraryproject.domain.dto.BookDto;
import com.libraryproject.mapper.BookMapper;
import com.libraryproject.service.BookDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/library/books")
@CrossOrigin("*")
@RequiredArgsConstructor
public class BooksController {

    BookMapper bookMapper;
    BookDbService dbService;

    @Autowired
    public BooksController(BookMapper bookMapper, BookDbService dbService) {
        this.bookMapper = bookMapper;
        this.dbService = dbService;
    }

    @GetMapping
    public ResponseEntity<List<BookDto>> getBooks(){
        List<BookDto> books = bookMapper.mapToBookDtoList(dbService.getAllBooks());
        return ResponseEntity.ok(books);
    }
    @GetMapping(value = "{bookId}")
    public ResponseEntity<BookDto> getBook(@PathVariable Long bookId){
        return ResponseEntity.ok(new BookDto(1L,"test","test",2002, 1));
    }
    @GetMapping("/top-month")
    public ResponseEntity<List<BookDto>> getTopBooks(){
        List<BookDto> topBooks = List.of(new BookDto(1L, "popular book1", "guy", 2002, 3),
                new BookDto(2L, "popular book2", "author", 2012, 4));
        return ResponseEntity.ok(topBooks);
    }

    @GetMapping(value = "/copies/{bookId}")
    public ResponseEntity<List<BookCopyDto>> getListOfCopies(@PathVariable Long bookId){
        List<BookCopyDto> bookCopies = List.of(new BookCopyDto(1L, 1L, "Rented"),
                new BookCopyDto(2L, 1L, "Available"));
        return ResponseEntity.ok(bookCopies);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BookDto> addBook(@RequestBody BookDto bookDto){
        return ResponseEntity.ok(bookDto);
    }
    @PostMapping(value = "/copies/add/{bookId}")
    public ResponseEntity<String> addBookCopy(@PathVariable Long bookId){
        return ResponseEntity.ok("copy added");
    }
    @PostMapping(value = "/copies/rent/{bookCopyId}")
    public ResponseEntity<String> rentBookCopy(@PathVariable Long bookCopyId){
        return ResponseEntity.ok("copy rented");
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BookDto> editBook(@RequestBody BookDto bookDto){
        return ResponseEntity.ok(bookDto);
    }

    @DeleteMapping(value = "{bookId}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long bookId){
        return ResponseEntity.ok().build();
    }
    @DeleteMapping(value = "/copies/{bookCopyId}")
    public ResponseEntity<Void> deleteBookCopy(@PathVariable Long bookCopyId){
        return ResponseEntity.ok().build();
    }
}
