package com.ks.library.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "books")
public class BookEntity {
    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String author;
    private int numberOfPages;

    public BookEntity() {}

    public BookEntity(String title, String author, int numberOfPages) {
        this.title = title;
        this.author = author;
        this.numberOfPages = numberOfPages;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }
}
