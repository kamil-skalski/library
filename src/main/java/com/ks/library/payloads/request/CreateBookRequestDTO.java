package com.ks.library.payloads.request;

import com.fasterxml.jackson.annotation.JsonCreator;

public class CreateBookRequestDTO {
    private final String title;
    private final String author;
    private final int numberOfPages;

    @JsonCreator
    public CreateBookRequestDTO(String title, String author, int numberOfPages) {
        this.title = title;
        this.author = author;
        this.numberOfPages = numberOfPages;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }
}
