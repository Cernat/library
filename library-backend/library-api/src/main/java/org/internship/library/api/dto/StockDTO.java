package org.internship.library.api.dto;

public class StockDTO
{

    private Integer id;
    private Integer numberOfBooks;
    private BookDTO bookID;

    public StockDTO()
    {
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Integer getNumberOfBooks()
    {
        return numberOfBooks;
    }

    public void setNumberOfBooks(Integer numberOfBooks)
    {
        this.numberOfBooks = numberOfBooks;
    }

    public BookDTO getBookID()
    {
        return bookID;
    }

    public void setBookID(BookDTO bookID)
    {
        this.bookID = bookID;
    }
}
