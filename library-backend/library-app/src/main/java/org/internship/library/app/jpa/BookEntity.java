package org.internship.library.app.jpa;

import org.internship.library.api.Book;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "book")
public class BookEntity implements Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private String title;
    private String author;
    private Integer numberOfPages;

    public BookEntity() {
    }

    public BookEntity(String id, String title, String author, Integer numberOfPages) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.numberOfPages = numberOfPages;
    }

    @Override
    public int getId() {
        return 0;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getTitle() {
        return null;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String getAuthor() {
        return null;
    }

    @Override
    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public Integer getNumberOfPages() {
        return null;
    }

    @Override
    public void setNumberOfPages(Integer numberOfPages) {
        this.numberOfPages = numberOfPages;
    }
}
