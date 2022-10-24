package com.news.api.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.news.api.client.GNewsClient;
import com.news.api.model.GNewsAPIResponse;
import com.news.api.service.TopHeadlinesService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TopHeadlinesServiceImpl implements TopHeadlinesService
{
    private final GNewsClient client;

    @Value("${gnews.api-key}")
    private String apiKey;
    
    @Override
    @Cacheable(cacheNames = "n_new_articles", key = "#max")
    public GNewsAPIResponse fetchNewArticles(Integer max)
    {
        return client.fetchNewArticles(max, apiKey).getBody();
    }
}
