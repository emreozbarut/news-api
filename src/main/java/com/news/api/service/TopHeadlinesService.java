package com.news.api.service;

import com.news.api.model.GNewsAPIResponse;

public interface TopHeadlinesService
{
    GNewsAPIResponse fetchNewArticles(Integer max);
}

