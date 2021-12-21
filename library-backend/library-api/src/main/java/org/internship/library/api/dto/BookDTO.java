package org.internship.library.api.dto;

import org.internship.library.api.book.Book;

import java.util.UUID;

public class BookDTO implements Book {

    private String id;
    private String title;
    private String author;
    private Integer numberOfPages;


    public BookDTO() {
        this.id = UUID.randomUUID().toString();
    }

    public BookDTO(Book book) {
        this.id = book.getId();
        this.title = book.getTitle();
        this.author = book.getAuthor();
        this.numberOfPages = book.getNumberOfPages();
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
