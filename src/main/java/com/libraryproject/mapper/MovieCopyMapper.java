package com.libraryproject.mapper;

import com.libraryproject.domain.MovieCopy;
import com.libraryproject.domain.dto.MovieCopyDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieCopyMapper {

    public MovieCopyDto mapToMovieCopyDto(MovieCopy movieCopy){
        return new MovieCopyDto(movieCopy.getId(),
                movieCopy.getMovie().getId(),
                movieCopy.getStatus());
    }
    public List<MovieCopyDto> mapToMovieCopyDtoList(List<MovieCopy> movieCopyList){
        return movieCopyList.stream()
                .map(this::mapToMovieCopyDto)
                .collect(Collectors.toList());
    }
}
