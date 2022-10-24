package com.news.api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.news.api.enums.SearchAttributes;
import com.news.api.model.GNewsAPIResponse;
import com.news.api.service.SearchService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/search")
public class SearchController
{
    private final SearchService searchService;
    
    @GetMapping
    public ResponseEntity<GNewsAPIResponse> searchByKeyword(@RequestParam("q") String keyword) {
        return ResponseEntity.ok(searchService.searchByKeyword(keyword));
    }

    @GetMapping("/article")
    public ResponseEntity<GNewsAPIResponse> fetchArticlesWith(@RequestParam(value = "title", required = false) String title,
                                                              @RequestParam(value = "author", required = false) String author,
                                                              @RequestParam(value = "attributes", defaultValue = "TITLE") List<SearchAttributes> searchAttributes) {
        return ResponseEntity.ok(searchService.searchWith(title, author, searchAttributes));
    }
}
