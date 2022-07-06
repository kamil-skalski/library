package com.ks.library.payloads.response;

public class BookResponseDTO {
    private final Long id;
    private final String title;
    private final String author;
    private final int numberOfPages;

    public BookResponseDTO(Long id, String title, String author, int numberOfPages) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.numberOfPages = numberOfPages;
    }

    public Long getId() {
        return id;
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
