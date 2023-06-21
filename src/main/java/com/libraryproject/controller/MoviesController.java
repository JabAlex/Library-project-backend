package com.libraryproject.controller;

import com.libraryproject.domain.Movie;
import com.libraryproject.domain.dto.DetailedBookDto;
import com.libraryproject.domain.dto.DetailedMovieDto;
import com.libraryproject.domain.dto.MovieCopyDto;
import com.libraryproject.domain.dto.MovieDto;
import com.libraryproject.mapper.MovieCopyMapper;
import com.libraryproject.mapper.MovieMapper;
import com.libraryproject.omdb.client.OmdbClient;
import com.libraryproject.service.MovieDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/library/movies")
@CrossOrigin("*")
@RequiredArgsConstructor
public class MoviesController {

    MovieMapper movieMapper;
    MovieCopyMapper movieCopyMapper;
    MovieDbService dbService;
    OmdbClient omdbClient;

    @Autowired
    public MoviesController(MovieMapper movieMapper, MovieDbService dbService, MovieCopyMapper movieCopyMapper, OmdbClient omdbClient) {
        this.movieMapper = movieMapper;
        this.dbService = dbService;
        this.movieCopyMapper = movieCopyMapper;
        this.omdbClient = omdbClient;
    }


    @GetMapping
    public ResponseEntity<List<MovieDto>> getMovies(){
        List<MovieDto> movies = movieMapper.mapToMovieDtoList(dbService.getAllMovies());
        return ResponseEntity.ok(movies);
    }
    @GetMapping(value = "{movieId}")
    public ResponseEntity<DetailedMovieDto> getMovie(@PathVariable Long movieId){
        MovieDto movieDto = movieMapper.mapToMovieDto(dbService.getMovie(movieId));
        DetailedMovieDto detailedMovieDto = DetailedMovieDto.builder()
                .id(movieDto.getId())
                .title(movieDto.getTitle())
                .director(movieDto.getDirector())
                .releaseYear(movieDto.getReleaseYear())
                .numberOfAvailableCopies(movieDto.getNumberOfAvailableCopies())
                .synopsis(omdbClient.getMovieDescription(movieDto.getTitle()))
                .build();
        return ResponseEntity.ok(detailedMovieDto);
    }
    @GetMapping("/top-month")
    public ResponseEntity<List<MovieDto>> getTopMovies(){
        List<MovieDto> topMovies = List.of(new MovieDto(1L, "popular movie", "guy guy", 2012, 2),
                new MovieDto(2L, "popular movie 2", "guy guy", 2002, 3));
        return ResponseEntity.ok(topMovies);
    }
    @GetMapping(value = "/copies/{movieId}")
    public ResponseEntity<List<MovieCopyDto>> getListOfCopies(@PathVariable Long movieId){
        List<MovieCopyDto> movieCopies = movieCopyMapper.mapToMovieCopyDtoList(dbService.getMovieCopies(movieId));
        return ResponseEntity.ok(movieCopies);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addMovie(@RequestBody MovieDto movieDto){
        dbService.saveMovie(movieMapper.mapToMovie(movieDto));
        return ResponseEntity.ok().build();
    }
    @PostMapping(value = "/copies/add/{movieId}")
    public ResponseEntity<Void> addMovieCopy(@PathVariable Long movieId){
        dbService.addMovieCopy(movieId);
        return ResponseEntity.ok().build();
    }
    @PutMapping(value = "/copies/rent/{movieCopyId}")
    public ResponseEntity<Void> rentMovieCopy(@PathVariable Long movieCopyId){
        dbService.rentMovieCopy(movieCopyId);
        return ResponseEntity.ok().build();
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> editMovie(@RequestBody MovieDto movieDto){
        Movie movie = dbService.getMovie(movieDto.getId());
        movie.setDirector(movieDto.getDirector());
        movie.setTitle(movieDto.getTitle());
        movie.setReleaseYear(movieDto.getReleaseYear());
        dbService.saveMovie(movie);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "{movieId}")
    public ResponseEntity<Void> deleteMovie(@PathVariable Long movieId){
        dbService.deleteMovie(movieId);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping(value = "/copies/{movieCopyId}")
    public ResponseEntity<Void> deleteMovieCopy(@PathVariable Long movieCopyId){
        dbService.deleteMovieCopy(movieCopyId);
        return ResponseEntity.ok().build();
    }
}
