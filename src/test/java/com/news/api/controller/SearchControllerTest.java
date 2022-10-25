package com.news.api.controller;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.news.api.service.impl.SearchServiceImpl;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(SearchController.class)
public class SearchControllerTest
{
    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private SearchServiceImpl searchService;
    
    @Test
    public void shouldPerformSearchByKeyword() throws Exception
    {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/search?q=test")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
        
        verify(searchService, times(1)).searchByKeyword("test");
    }

    @Test
    public void shouldPerformFetchArticlesWithTitleAndAuthor() throws Exception
    {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/search/article?title=test&author=test")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());

        verify(searchService, times(1)).searchWith(anyString(), anyString(), anyList());
    }
}