package com.news.api.exception.topheadline;

import com.news.api.exception.BaseException;

public class TopHeadlinesException extends BaseException
{
    private static final long serialVersionUID = 974753689719574692L;

    public TopHeadlinesException(int code, String message, int statusCode)
    {
        super(code, message, statusCode);
    }
}
