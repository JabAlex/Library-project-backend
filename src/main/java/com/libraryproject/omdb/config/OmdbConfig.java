package com.libraryproject.omdb.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class OmdbConfig {
    @Value("${omdb.api.endpoint}")
    private String omdbApiEndpoint;
    @Value("${omdb.api.key}")
    private String omdbApiKey;
}
