package com.news.api.enums;

import lombok.Getter;

@Getter
public enum SearchAttributes
{
    TITLE("title"),
    DESCRIPTION("description"),
    CONTENT("content");

    private final String name;

    SearchAttributes(String name)
    {
        this.name = name;
    }
}
