package com.news.api.config;

import java.util.concurrent.TimeUnit;

import org.springframework.context.annotation.Bean;

import feign.Request;

public class FeignClientConfiguration
{
    private static final long CONNECT_TIMEOUT_SECOND = 5L;
    private static final long READ_TIMEOUT_SECOND = 10L;
    private static final boolean FOLLOW_REDIRECTS = false;

    @Bean
    public FeignClientErrorDecoder portfolioServiceClientErrorDecoder() {
        return new FeignClientErrorDecoder();
    }

    @Bean
    public Request.Options options() {
        return new Request.Options(
                CONNECT_TIMEOUT_SECOND,
                TimeUnit.SECONDS,
                READ_TIMEOUT_SECOND,
                TimeUnit.SECONDS,
                FOLLOW_REDIRECTS);
    }
}
