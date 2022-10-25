package com.news.api.exception.search;

import com.news.api.exception.BaseException;

public class SearchException extends BaseException
{
    private static final long serialVersionUID = 2350289311502920464L;

    public SearchException(int code, String message, int statusCode)
    {
        super(code, message, statusCode);
    }
}
