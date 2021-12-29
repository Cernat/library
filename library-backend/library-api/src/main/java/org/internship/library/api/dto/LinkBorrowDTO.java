package org.internship.library.api.dto;

public class LinkBorrowDTO
{

    private Integer id;
    private UserDTO user;
    private BookDTO book;
    private BorrowDTO borrow;

    public LinkBorrowDTO()
    {
    }

    public LinkBorrowDTO(Integer id, UserDTO user, BookDTO book, BorrowDTO borrow)
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

    public UserDTO getUser()
    {
        return user;
    }

    public void setUser(UserDTO user)
    {
        this.user = user;
    }

    public BookDTO getBook()
    {
        return book;
    }

    public void setBook(BookDTO book)
    {
        this.book = book;
    }

    public BorrowDTO getBorrow()
    {
        return borrow;
    }

    public void setBorrow(BorrowDTO borrow)
    {
        this.borrow = borrow;
    }
}
