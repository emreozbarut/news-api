package com.news.api.controller;

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

import com.news.api.service.impl.TopHeadlinesServiceImpl;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(TopHeadlinesController.class)
public class TopHeadlinesControllerTest
{
    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private TopHeadlinesServiceImpl topHeadlinesService;

    @Test
    public void shouldPerformFetchNewArticles() throws Exception
    {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/top-headline/new/article?max=10")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());

        verify(topHeadlinesService, times(1)).fetchNewArticles(10);
    }
}