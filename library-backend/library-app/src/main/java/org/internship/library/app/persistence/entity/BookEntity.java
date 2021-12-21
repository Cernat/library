package org.internship.library.app.persistence.entity;

import org.internship.library.api.book.Book;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

/**
 * Implements the {@link Book} model for JPA.
 */
@Entity(name = "book")
public class BookEntity {

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public void setAuthor(String author) { this.author = author; }

    public Integer getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(Integer numberOfPages) {
        this.numberOfPages = numberOfPages;
    }
}
