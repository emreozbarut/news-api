package com.news.api.service;

import java.util.List;

import com.news.api.enums.SearchAttributes;
import com.news.api.model.GNewsAPIResponse;

public interface SearchService
{
    GNewsAPIResponse searchByKeyword(String keyword);

    GNewsAPIResponse searchWith(String title, String author, List<SearchAttributes> attributes);
}
