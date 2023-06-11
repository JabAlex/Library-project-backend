package com.libraryproject.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {
    private Long bookId;
    private String title;
    private String author;
    private int releaseYear;
    private int numberOfAvailableCopies;
}
