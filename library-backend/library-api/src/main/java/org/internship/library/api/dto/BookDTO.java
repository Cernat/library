package org.internship.library.api.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BookDTO implements Serializable
{

    private String id;
    private String title;
    private Integer numberOfPages;
    private AuthorDTO author;
    private StockDTO stock;
    private List<LinkBorrowDTO> linkBorrow = new ArrayList<>();

    public BookDTO()
    {
        this.id = UUID.randomUUID().toString();
    }

    public BookDTO(String id, String title, Integer numberOfPages, AuthorDTO author, StockDTO stock, List<LinkBorrowDTO> linkBorrow) {
        this.id = id;
        this.title = title;
        this.numberOfPages = numberOfPages;
        this.author = author;
        this.stock = stock;
        this.linkBorrow = linkBorrow;
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

    public StockDTO getStock()
    {
        return stock;
    }

    public void setStock(StockDTO stock)
    {
        this.stock = stock;
    }

    public List<LinkBorrowDTO> getLinkBorrow() {
        return linkBorrow;
    }

    public void setLinkBorrow(List<LinkBorrowDTO> linkBorrow) {
        this.linkBorrow = linkBorrow;
    }
}
