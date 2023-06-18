package com.libraryproject.domain.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class DetailedMovieDto {
    private Long id;
    private String title;
    private String director;
    private int releaseYear;
    private int numberOfAvailableCopies;
    private String synopsis;
}

