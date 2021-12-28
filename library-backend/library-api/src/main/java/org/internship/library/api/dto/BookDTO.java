package org.internship.library.api.dto;

import java.io.Serializable;
import java.util.UUID;

public class BookDTO implements Serializable
{

    private String id;
    private String title;
    private String author;
    private Integer numberOfPages;

    public BookDTO()
    {
        this.id = UUID.randomUUID().toString();
    }

    public BookDTO(String id, String title, String author, Integer numberOfPages)
    {
        this.id = id;
        this.title = title;
        this.author = author;
        this.numberOfPages = numberOfPages;
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
