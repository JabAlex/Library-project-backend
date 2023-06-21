package com.libraryproject.omdb.client;

import com.libraryproject.omdb.config.OmdbConfig;
import com.libraryproject.omdb.dto.OmdbResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OmdbClient {

    private final OmdbConfig omdbConfig;
    private final RestTemplate restTemplate;
    public String getMovieDescription(String movieName){
        URI url = UriComponentsBuilder
                .fromHttpUrl(omdbConfig.getOmdbApiEndpoint())
                .queryParam("apikey", omdbConfig.getOmdbApiKey())
                .queryParam("t", movieName)
                .queryParam("plot", "full")
                .build()
                .encode()
                .toUri();
        try{
            OmdbResponseDto response = restTemplate.getForObject(url, OmdbResponseDto.class);
            return response.getPlot();
        } catch (RestClientException e){
            return "";
        }
    }
}
