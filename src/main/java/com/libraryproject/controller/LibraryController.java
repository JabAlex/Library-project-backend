package com.libraryproject.controller;

import com.libraryproject.domain.dto.ArticleDto;
import com.libraryproject.domain.dto.ProfileDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("v1/library")
@CrossOrigin("*")
@RequiredArgsConstructor
public class LibraryController {

    @GetMapping("/news")
    public ResponseEntity<List<ArticleDto>> getAllNews(){
        List<ArticleDto> articles = List.of(new ArticleDto("admin", "test article 1", LocalDate.now()),
                new ArticleDto("admin", "test article 2", LocalDate.of(2023, 2, 2)));
        return ResponseEntity.ok(articles);
    }
    @GetMapping("/news/recent")
    public ResponseEntity<List<ArticleDto>> getRecentNews(){
        List<ArticleDto> recentArticles = List.of(new ArticleDto("admin", "test article 1", LocalDate.now()));
        return ResponseEntity.ok(recentArticles);
    }

    @PostMapping(value = "/news", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> postArticle(@RequestBody ArticleDto articleDto){
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/profile/{profileID}")
    public ResponseEntity<ProfileDto> getProfileInfo(@PathVariable Long profileID){
        return ResponseEntity.ok(new ProfileDto());
    }
}
