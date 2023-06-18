package com.libraryproject.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "MOVIES")
public class Movie {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "TITLE")
    private String title;
    @Column(name = "DIRECTOR")
    private String director;
    @Column(name = "RELEASE_YEAR")
    private int releaseYear;
    @OneToMany(
            targetEntity = MovieCopy.class,
            mappedBy = "movie",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<MovieCopy> movieCopies;
}
