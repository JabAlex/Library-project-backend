package com.libraryproject.omdb.client;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = OmdbClient.class)
public class OmdbClientTest {

    @Autowired
    OmdbClient omdbClient;

    @Test
    void testGetMovieDescription(){
        //Given
        String movieName = "Dune";
        String response;
        //When
        response = omdbClient.getMovieDescription(movieName);
        //Then
        assertNotNull(response);
        assertNotEquals("", response);
    }
}
