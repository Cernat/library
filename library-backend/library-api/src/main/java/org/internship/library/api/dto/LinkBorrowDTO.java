package org.internship.library.api.dto;

public class LinkBorrowDTO
{

    private Integer id;
    private Integer user;
    private String book;
    private Integer borrow;

    public LinkBorrowDTO()
    {
    }

    public LinkBorrowDTO(Integer id, Integer user, String book, Integer borrow)
    {
        this.id = id;
        this.user = user;
        this.book = book;
        this.borrow = borrow;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Integer getUser()
    {
        return user;
    }

    public void setUser(Integer user)
    {
        this.user = user;
    }

    public String getBook()
    {
        return book;
    }

    public void setBook(String book)
    {
        this.book = book;
    }

    public Integer getBorrow()
    {
        return borrow;
    }

    public void setBorrow(Integer borrow)
    {
        this.borrow = borrow;
    }
}
