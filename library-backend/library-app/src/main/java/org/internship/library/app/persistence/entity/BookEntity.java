package org.internship.library.app.persistence.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

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
    private Integer numberOfPages;

    @ManyToOne
    @JoinColumn(name = "author_ID")
    private AuthorEntity author;

    @JoinColumn(name = "stock_ID")
    private StockEntity stock;

    @OneToMany(mappedBy = "book", cascade = CascadeType.PERSIST)
    private List<LinkBorrowEntity> linkBorrow = new ArrayList<>();

    public BookEntity()
    {
        this.id = UUID.randomUUID().toString();
    }

    public BookEntity(String id, String title, Integer numberOfPages, AuthorEntity author, StockEntity stock, List<LinkBorrowEntity> linkBorrow) {
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

    public AuthorEntity getAuthor()
    {
        return author;
    }

    public void setAuthor(AuthorEntity author)
    {
        this.author = author;
    }

    public StockEntity getStock()
    {
        return stock;
    }

    public void setStock(StockEntity stock)
    {
        this.stock = stock;
    }

    public List<LinkBorrowEntity> getLinkBorrow() {
        return linkBorrow;
    }

    public void setLinkBorrow(List<LinkBorrowEntity> linkBorrow) {
        this.linkBorrow = linkBorrow;
    }
}
