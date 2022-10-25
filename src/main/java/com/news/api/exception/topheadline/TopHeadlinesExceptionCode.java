package com.news.api.exception.topheadline;

import lombok.Getter;

@Getter
public enum TopHeadlinesExceptionCode
{
    INVALID_MAX_VALUE(1);

    private final int code;

    TopHeadlinesExceptionCode(int code)
    {
        this.code = code;
    }
}
