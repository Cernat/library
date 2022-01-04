package org.internship.library.api.dto;

import java.io.Serializable;
import java.util.UUID;

public class BookDTO implements Serializable
{

    private String id;
    private String title;
    private Integer numberOfPages;
    private AuthorDTO author;

    public BookDTO()
    {
        this.id = UUID.randomUUID().toString();
    }

    public BookDTO(String id, String title, Integer numberOfPages, AuthorDTO author)
    {
        this.id = id;
        this.title = title;
        this.numberOfPages = numberOfPages;
        this.author = author;
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

    public Integer getNumberOfPages()
    {

        return numberOfPages;
    }

    public void setNumberOfPages(Integer numberOfPages)
    {
        this.numberOfPages = numberOfPages;
    }

    public AuthorDTO getAuthor()
    {
        return author;
    }

    public void setAuthor(AuthorDTO author)
    {
        this.author = author;
    }

}
