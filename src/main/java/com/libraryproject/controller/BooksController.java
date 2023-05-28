package com.libraryproject.controller;

import com.libraryproject.domain.dto.BookDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/library/books")
@CrossOrigin("*")
@RequiredArgsConstructor
public class BooksController {

    @GetMapping
    public ResponseEntity<List<BookDto>> getBooks(){
        List<BookDto> books = List.of(new BookDto(2L, "test", "test", 1998),
                new BookDto(1L, "test2", "test2", 2020));
        return ResponseEntity.ok(books);
    }
    @GetMapping(value = "{bookId}")
    public ResponseEntity<BookDto> getBook(@PathVariable Long bookId){
        return ResponseEntity.ok(new BookDto(1L,"test","test",2002));
    }
    @GetMapping("/top-month")
    public ResponseEntity<List<BookDto>> getTopBooks(){
        List<BookDto> topBooks = List.of(new BookDto(1L, "popular book1", "guy", 2002),
                new BookDto(2L, "popular book2", "author", 2012));
        return ResponseEntity.ok(topBooks);
    }
    @GetMapping(value = "/copies/quantity/{bookId}")
    public ResponseEntity<Integer> getNumberOfAvailableCopies(@PathVariable Long bookId){
        return ResponseEntity.ok(3);
    }
    @GetMapping(value = "/copies/status/{bookCopyId}")
    public ResponseEntity<String> getCopyStatus(@PathVariable Long bookCopyId){
        return ResponseEntity.ok("Available");
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
