package com.libraryproject.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class HapiResponseByNameDto {
    int book_id;
    String name;
    String cover;
    String url;
    List<String> authors;
    double rating;
    int year;
    String synopsis;
}
