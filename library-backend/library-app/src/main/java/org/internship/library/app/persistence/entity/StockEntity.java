package org.internship.library.app.persistence.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "stock")
public class StockEntity implements Serializable
{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Integer numberOfBooks;

    @OneToOne
    @JoinColumn(name = "book_ID")
    private BookEntity bookID;

    public StockEntity()
    {
    }

    public StockEntity(Integer id, Integer numberOfBooks, BookEntity bookID)
    {
        this.id = id;
        this.numberOfBooks = numberOfBooks;
        this.bookID = bookID;
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

    public BookEntity getBookID()
    {
        return bookID;
    }

    public void setBookID(BookEntity bookID)
    {
        this.bookID = bookID;
    }
}
