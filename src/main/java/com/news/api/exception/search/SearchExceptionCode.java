package com.news.api.exception.search;

import lombok.Getter;

@Getter
public enum SearchExceptionCode
{
    INVALID_TITLE(1),
    INVALID_AUTHOR(2),
    INVALID_SEARCH_REQUEST(3),
    INVALID_KEYWORD(4);
    
    private final int code;
    
    SearchExceptionCode(int code)
    {
        this.code = code;
    }
}
