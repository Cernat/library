package org.internship.library.app.persistence.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.internship.library.api.Book;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity(name = "book")
public class BookEntity implements Book {

    @Id
    private String id;
    private String title;
    private String author;
    private Integer numberOfPages;


    public BookEntity() {
        this.id = UUID.randomUUID().toString();
    }

    public BookEntity(Book book) {
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
