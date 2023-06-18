package com.libraryproject.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentedBookDto {
    private Long bookId;
    private Long bookCopyId;
    private Long profileId;
    private String title;
    private String author;
}
