package com.libraryproject.hapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HapiResponseByIdDto {
    int book_id;
    String name;
    String cover;
    String url;
    List<String> authors;
    double rating;
    int pages;
    String published_date;
    String synopsis;
}
