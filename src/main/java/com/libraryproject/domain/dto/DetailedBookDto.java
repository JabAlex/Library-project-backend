package com.libraryproject.domain.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class DetailedBookDto {
    private Long id;
    private String title;
    private String author;
    private int releaseYear;
    private int numberOfAvailableCopies;
    private String synopsis;
}