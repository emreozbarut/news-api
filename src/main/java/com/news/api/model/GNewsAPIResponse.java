package com.news.api.model;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GNewsAPIResponse implements Serializable
{
    private static final long serialVersionUID = 6669596673790475000L;
    
    private int totalArticles;
    private List<Article> articles;
}
