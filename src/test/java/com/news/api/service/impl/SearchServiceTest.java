package com.news.api.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.news.api.client.GNewsClient;
import com.news.api.enums.SearchAttributes;
import com.news.api.exception.search.SearchException;
import com.news.api.exception.search.SearchExceptionCode;
import com.news.api.model.Article;
import com.news.api.model.GNewsAPIResponse;
import com.news.api.model.Source;

@ExtendWith(MockitoExtension.class)
public class SearchServiceTest
{
    @InjectMocks
    private SearchServiceImpl searchService;
    
    @Mock
    private GNewsClient client;
    
    @Test
    public void shouldSearchByKeyword() {
        String template = "test";
        Article article = Article.builder()
                .content(template)
                .description(template)
                .image(template)
                .url(template)
                .title(template)
                .source(Source.builder().name(template).url(template).build())
                .build();
        
        GNewsAPIResponse mockResponse = GNewsAPIResponse.builder()
                .totalArticles(10)
                .articles(Collections.singletonList(article))
                .build();
        
        when(client.searchByKeyword(anyString(), any())).thenReturn(ResponseEntity.of(Optional.of(mockResponse)));
        
        GNewsAPIResponse response = searchService.searchByKeyword(template);
        
        assertEquals(mockResponse.getTotalArticles(), response.getTotalArticles());
        assertEquals(mockResponse.getArticles().size(), response.getArticles().size());
    }
    
    @Test
    public void shouldThrowSearchException_whenKeywordIsNull()
    {
        try
        {
            searchService.searchByKeyword(null);
        }
        catch (SearchException se)
        {
            assertEquals(SearchExceptionCode.INVALID_KEYWORD.getCode(), se.getCode());
            assertEquals(HttpStatus.BAD_REQUEST.value(), se.getStatusCode());
        }
    }
    
    @Test
    public void shouldSearchWithTitle_whenAuthorIsBlank()
    {
        String template = "test";
        Article article = Article.builder()
                .content(template)
                .description(template)
                .image(template)
                .url(template)
                .title(template)
                .source(Source.builder().name(template).url(template).build())
                .build();

        GNewsAPIResponse mockResponse = GNewsAPIResponse.builder()
                .totalArticles(10)
                .articles(Collections.singletonList(article))
                .build();
        
        when(client.searchArticlesWithAttributes(anyString(), anyList(), any())).thenReturn(ResponseEntity.of(Optional.of(mockResponse)));
        
        GNewsAPIResponse response = searchService.searchWith(template, null, Collections.singletonList(SearchAttributes.TITLE));
        
        assertEquals(mockResponse.getTotalArticles(), response.getTotalArticles());
        assertEquals(mockResponse.getArticles().size(), response.getArticles().size());
        
        verify(client, times(1)).searchArticlesWithAttributes(anyString(), anyList(), any());
        verifyNoMoreInteractions(client);
    }

    @Test
    public void shouldSearchWithAuthor_whenTitleIsBlank()
    {
        String template = "test";
        Article article = Article.builder()
                .content(template)
                .description(template)
                .image(template)
                .url(template)
                .title(template)
                .source(Source.builder().name(template).url(template).build())
                .build();

        GNewsAPIResponse mockResponse = GNewsAPIResponse.builder()
                .totalArticles(10)
                .articles(Collections.singletonList(article))
                .build();

        when(client.searchByKeyword(anyString(), any())).thenReturn(ResponseEntity.of(Optional.of(mockResponse)));

        GNewsAPIResponse response = searchService.searchWith(null, template, Collections.singletonList(SearchAttributes.TITLE));

        assertEquals(1, response.getTotalArticles());
        assertEquals(mockResponse.getArticles().size(), response.getArticles().size());

        verify(client, times(1)).searchByKeyword(anyString(), any());
        verifyNoMoreInteractions(client);
    }

    @Test
    public void shouldSearchWithAuthorAndTitle_when2OfThemIsNotBlank()
    {
        String template = "test";
        Article article = Article.builder()
                .content(template)
                .description(template)
                .image(template)
                .url(template)
                .title(template)
                .source(Source.builder().name(template).url(template).build())
                .build();

        GNewsAPIResponse mockResponse = GNewsAPIResponse.builder()
                .totalArticles(10)
                .articles(Collections.singletonList(article))
                .build();

        when(client.searchArticlesWithAttributes(anyString(), anyList(), any())).thenReturn(ResponseEntity.of(Optional.of(mockResponse)));

        GNewsAPIResponse response = searchService.searchWith(template, null, Collections.singletonList(SearchAttributes.TITLE));

        assertEquals(mockResponse.getTotalArticles(), response.getTotalArticles());
        assertEquals(mockResponse.getArticles().size(), response.getArticles().size());

        verify(client, times(1)).searchArticlesWithAttributes(anyString(), anyList(), any());
        verifyNoMoreInteractions(client);
    }
    
    @Test
    public void shouldThrowSearchException_whenTitleAndAuthorIsBlank()
    {
        try
        {
            searchService.searchWith(null, null, Collections.singletonList(SearchAttributes.TITLE));
        }
        catch (SearchException se)
        {
            assertEquals(SearchExceptionCode.INVALID_SEARCH_REQUEST.getCode(), se.getCode());
            assertEquals(HttpStatus.BAD_REQUEST.value(), se.getStatusCode());
        }
    }
}