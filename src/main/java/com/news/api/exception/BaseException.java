package com.news.api.exception;

import lombok.Getter;

@Getter
public class BaseException extends RuntimeException
{
    private static final long serialVersionUID = 4228117161839738850L;
    
    private final int code;
    private final String message;
    private final int statusCode;
    
    public BaseException(int code, String message, int statusCode)
    {
        super(message);
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }
}
