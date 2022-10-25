package com.news.api.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
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
import com.news.api.exception.topheadline.TopHeadlinesException;
import com.news.api.exception.topheadline.TopHeadlinesExceptionCode;
import com.news.api.model.Article;
import com.news.api.model.GNewsAPIResponse;
import com.news.api.model.Source;

@ExtendWith(MockitoExtension.class)
class TopHeadlinesServiceTest
{
    @InjectMocks
    private TopHeadlinesServiceImpl topHeadlinesService;
    
    @Mock
    private GNewsClient client;
    
    @Test
    public void shouldFetchNewArticles()
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
        
        when(client.fetchNewArticles(anyInt(), any())).thenReturn(ResponseEntity.of(Optional.of(mockResponse)));
        
        GNewsAPIResponse response = topHeadlinesService.fetchNewArticles(1);
        
        assertEquals(mockResponse.getTotalArticles(), response.getTotalArticles());
        assertEquals(mockResponse.getArticles().size(), response.getArticles().size());
        
        verify(client, times(1)).fetchNewArticles(anyInt(), any());
    }
    
    @Test
    public void shouldThrowTopHeadlinesException_whenMaxValueIsNull()
    {
        try
        {
            topHeadlinesService.fetchNewArticles(null);
        }
        catch (TopHeadlinesException the)
        {
            assertEquals(TopHeadlinesExceptionCode.INVALID_MAX_VALUE.getCode(), the.getCode());
            assertEquals(HttpStatus.BAD_REQUEST.value(), the.getStatusCode());
        }
    }
}