package com.news.api.controller.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.news.api.exception.topheadline.TopHeadlinesException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class TopHeadlinesControllerAdvice
{
    @ExceptionHandler(value = {
            TopHeadlinesException.class
    })
    protected ResponseEntity<String> handleExceptions(TopHeadlinesException the)
    {
        log.error("ErrorCode: {}, Message: {}", the.getCode(), the.getMessage());
        return new ResponseEntity<>(the.getMessage(), HttpStatus.valueOf(the.getStatusCode()));
    }
}
