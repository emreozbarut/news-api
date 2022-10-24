package com.news.api.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.news.api.client.GNewsClient;
import com.news.api.enums.SearchAttributes;
import com.news.api.model.Article;
import com.news.api.model.GNewsAPIResponse;
import com.news.api.service.SearchService;

import io.micrometer.core.instrument.util.StringUtils;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SearchServiceImpl implements SearchService
{
    private static final Pattern REGEX = Pattern.compile("[^A-Za-z0-9]");
    
    private final GNewsClient client;
    
    @Value("${gnews.api-key}")
    private String apiKey;
    
    @Override
    @Cacheable(cacheNames = "searches_by_keyword", key = "#keyword")
    public GNewsAPIResponse searchByKeyword(String keyword)
    {
        return client.searchByKeyword(validateAndFixKeyword(keyword), apiKey).getBody();
    }

    @Override
    @Cacheable(cacheNames = "searches_by_attribute", key = "#title + #author + #attributes")
    public GNewsAPIResponse searchWith(String title, String author, List<SearchAttributes> attributes)
    {
        if (StringUtils.isBlank(author) && StringUtils.isNotBlank(title))
        {
            return client.searchArticlesWithAttributes(validateAndFixKeyword(title), attributes, apiKey).getBody();
        }
        else if (StringUtils.isNotBlank(author) && StringUtils.isBlank(title))
        {
            return filterArticlesBy(author);
        }
        
        GNewsAPIResponse response = client.searchArticlesWithAttributes(validateAndFixKeyword(title), attributes, apiKey).getBody();
        return filterArticlesByAuthor(author, response);
    }

    private GNewsAPIResponse filterArticlesBy(String author)
    {
        GNewsAPIResponse response = client.searchByKeyword(validateAndFixKeyword(author), apiKey).getBody();
        return filterArticlesByAuthor(author, response);
    }

    private GNewsAPIResponse filterArticlesByAuthor(String author, GNewsAPIResponse response)
    {
        if (Objects.isNull(response)) {
            return null;
        }
        
        if (CollectionUtils.isEmpty(response.getArticles())) {
            return response;
        }
        
        List<Article> filteredArticles = response.getArticles().stream()
                .filter(article -> article.getSource().getName().contains(author))
                .collect(Collectors.toList());

        return GNewsAPIResponse.builder()
                .totalArticles(filteredArticles.size())
                .articles(filteredArticles)
                .build();
    }
    
    private String validateAndFixKeyword(String keyword) {
        Matcher matcher = REGEX.matcher(keyword);
        if (matcher.find()) {
            return String.format("\"%s\"", keyword);
        }
        return keyword;
    }
}
