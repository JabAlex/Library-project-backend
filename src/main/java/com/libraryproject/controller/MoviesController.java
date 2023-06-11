package com.libraryproject.controller;

import com.libraryproject.domain.dto.MovieCopyDto;
import com.libraryproject.domain.dto.MovieDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/library/movies")
@CrossOrigin("*")
@RequiredArgsConstructor
public class MoviesController {

    @GetMapping
    public ResponseEntity<List<MovieDto>> getMovies(){
        List<MovieDto> movies = List.of(new MovieDto(1L, "test", "test", 2022, 2),
                new MovieDto(2L, "test2", "tetst", 2012, 3));
        return ResponseEntity.ok(movies);
    }
    @GetMapping(value = "{movieId}")
    public ResponseEntity<MovieDto> getMovie(@PathVariable Long movieId){
        return ResponseEntity.ok(new MovieDto(1L, "test", "test", 2020, 1));
    }
    @GetMapping("/top-month")
    public ResponseEntity<List<MovieDto>> getTopMovies(){
        List<MovieDto> topMovies = List.of(new MovieDto(1L, "popular movie", "guy guy", 2012, 2),
                new MovieDto(2L, "popular movie 2", "guy guy", 2002, 3));
        return ResponseEntity.ok(topMovies);
    }
    @GetMapping(value = "/copies/{movieId}")
    public ResponseEntity<List<MovieCopyDto>> getListOfCopies(@PathVariable Long movieId){
        List<MovieCopyDto> movieCopies = List.of(new MovieCopyDto(1L, 1L, "Available"),
                new MovieCopyDto(2L, 1L, "Rented"));
        return ResponseEntity.ok(movieCopies);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MovieDto> addMovie(@RequestBody MovieDto movieDto){
        return ResponseEntity.ok(movieDto);
    }
    @PostMapping(value = "/copies/add/{movieId}")
    public ResponseEntity<String> addMovieCopy(@PathVariable Long movieId){
        return ResponseEntity.ok("copy added");
    }
    @PostMapping(value = "/copies/rent/{movieCopyId}")
    public ResponseEntity<String> rentMovieCopy(@PathVariable Long movieCopyId){
        return ResponseEntity.ok("copy rented");
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MovieDto> editMovie(@RequestBody MovieDto movieDto){
        return ResponseEntity.ok(movieDto);
    }

    @DeleteMapping(value = "{movieId}")
    public ResponseEntity<Void> deleteMovie(@PathVariable Long movieId){
        return ResponseEntity.ok().build();
    }
    @DeleteMapping(value = "/copies/{movieCopyId}")
    public ResponseEntity<Void> deleteMovieCopy(@PathVariable Long movieCopyId){
        return ResponseEntity.ok().build();
    }
}
