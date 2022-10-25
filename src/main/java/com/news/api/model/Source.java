package com.news.api.model;

import java.io.Serializable;

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
public class Source implements Serializable
{
    private static final long serialVersionUID = -8135441867802181371L;
    
    private String name;
    private String url;
}
