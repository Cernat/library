package org.internship.library.api.dto;

public class StockDTO
{

    private Integer id;
    private Integer numberOfBooks;
    private String bookID;

    public StockDTO(Integer id, Integer numberOfBooks, String bookID)
    {
        this.id = id;
        this.numberOfBooks = numberOfBooks;
        this.bookID = bookID;
    }

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

    public String getBookID()
    {
        return bookID;
    }

    public void setBookID(String bookID)
    {
        this.bookID = bookID;
    }
}
