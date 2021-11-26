package org.internship.library.client;

import org.internship.library.api.Book;

public class BookModel implements Book {

    private String id;
    private String title;
    private String author;
    private Integer numberOfPages;

    public BookModel() {
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String getAuthor() {
        return author;
    }

    @Override
    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public Integer getNumberOfPages() {
        return numberOfPages;
    }

    @Override
    public void setNumberOfPages(Integer numberOfPages) {
        this.numberOfPages = numberOfPages;
    }
}
