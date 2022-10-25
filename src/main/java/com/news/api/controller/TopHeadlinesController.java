package com.news.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.news.api.model.GNewsAPIResponse;
import com.news.api.service.TopHeadlinesService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/top-headline")
public class TopHeadlinesController
{
    private final TopHeadlinesService service;

    @GetMapping("/new/article")
    public ResponseEntity<GNewsAPIResponse> fetchNewArticles(@RequestParam("max") Integer max)
    {
        return ResponseEntity.ok(service.fetchNewArticles(max));
    }
}
