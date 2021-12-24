package org.internship.library.app.persistence.entity;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.internship.library.api.dto.BookDTO;

/**
 * Implements the {@link BookDTO} model for JPA.
 */
@Entity(name = "book")
public class BookEntity implements Serializable
{

    @Id
    private String id;
    private String title;
    private String author;
    private Integer numberOfPages;

    public BookEntity()
    {
        this.id = UUID.randomUUID().toString();
    }

    public BookEntity(BookDTO book)
    {
        this.id = book.getId();
        this.title = book.getTitle();
        this.author = book.getAuthor();
        this.numberOfPages = book.getNumberOfPages();
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getAuthor()
    {
        return author;
    }

    public void setAuthor(String author)
    {
        this.author = author;
    }

    public Integer getNumberOfPages()
    {
        return numberOfPages;
    }

    public void setNumberOfPages(Integer numberOfPages)
    {
        this.numberOfPages = numberOfPages;
    }
}
