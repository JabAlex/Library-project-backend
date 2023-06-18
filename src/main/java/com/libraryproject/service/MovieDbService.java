package com.libraryproject.service;

import com.libraryproject.domain.Movie;
import com.libraryproject.domain.MovieCopy;
import com.libraryproject.domain.repository.MovieCopyRepository;
import com.libraryproject.domain.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieDbService {

    private final MovieRepository movieRepository;
    private final MovieCopyRepository movieCopyRepository;

    public List<Movie> getAllMovies(){
        return movieRepository.findAll();
    }
    public Movie getMovie(Long movieId){
        return movieRepository.findById(movieId).orElse(null);
    }
    public List<MovieCopy> getMovieCopies(Long movieId){
        return movieCopyRepository.findMovieCopiesById(movieId);
    }
    public void saveMovie(Movie movie){
        movieRepository.save(movie);
    }
    public void addMovieCopy(Long movieId){
        Movie movie = movieRepository.findById(movieId).orElse(null);
        MovieCopy movieCopy = new MovieCopy(
                null,
                movie,
                "Available");
        movieCopyRepository.save(movieCopy);
    }
    public void deleteMovie(Long movieId){
        movieRepository.deleteById(movieId);
    }
    public void deleteMovieCopy(Long movieCopyId){
        movieCopyRepository.deleteById(movieCopyId);
    }
    public void rentMovieCopy(Long movieCopyId){
        MovieCopy movieCopy = movieCopyRepository.findById(movieCopyId).orElse(null);
        movieCopy.setStatus("Rented");
        movieCopyRepository.save(movieCopy);
    }
}
