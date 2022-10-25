package com.news.api.controller.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.news.api.exception.search.SearchException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class SearchControllerAdvice
{
    @ExceptionHandler(value = {
            SearchException.class
    })
    protected ResponseEntity<String> handleExceptions(SearchException se)
    {
        log.error("ErrorCode: {}, Message: {}", se.getCode(), se.getMessage());
        return new ResponseEntity<>(se.getMessage(), HttpStatus.valueOf(se.getStatusCode()));
    }
}
