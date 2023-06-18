package com.libraryproject.mapper;

import com.libraryproject.domain.Movie;
import com.libraryproject.domain.dto.MovieDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieMapper {

    public MovieDto mapToMovieDto(Movie movie){
        return new MovieDto(movie.getId(),
                movie.getTitle(),
                movie.getDirector(),
                movie.getReleaseYear(),
                movie.getMovieCopies().size());
    }

    public List<MovieDto> mapToMovieDtoList(List<Movie> movieList){
        return movieList.stream()
                .map(this::mapToMovieDto)
                .collect(Collectors.toList());
    }
    public Movie mapToMovie(MovieDto movieDto){
        return new Movie(movieDto.getMovieId(),
                movieDto.getTitle(),
                movieDto.getDirector(),
                movieDto.getReleaseYear(),
                new ArrayList<>());
    }
}
