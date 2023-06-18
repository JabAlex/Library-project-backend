package com.libraryproject.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "MOVIE_COPIES")
public class MovieCopy {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    @JoinColumn(name = "MOVIE_ID")
    private Movie movie;
    @Column(name = "STATUS")
    private String status;
}
