package org.internship.library.api.dto;

import java.util.ArrayList;
import java.util.List;

public class AuthorDTO
{

    private Integer id;
    private String first_name;
    private String last_name;
    private List<BookDTO> books = new ArrayList<>();

    public AuthorDTO()
    {
    }

    public AuthorDTO(Integer id, String first_name, String last_name, List<BookDTO> books)
    {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.books = books;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getFirst_name()
    {
        return first_name;
    }

    public void setFirst_name(String first_name)
    {
        this.first_name = first_name;
    }

    public String getLast_name()
    {
        return last_name;
    }

    public void setLast_name(String last_name)
    {
        this.last_name = last_name;
    }

    public List<BookDTO> getBooks()
    {
        return books;
    }

    public void setBooks(List<BookDTO> books)
    {
        this.books = books;
    }
}
