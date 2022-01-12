package org.internship.library.app.persistence.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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
    @Column(name = "id")
    private Integer id;

    @Column(name = "number_of_books")
    private Integer numberOfBooks;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "book_id", referencedColumnName = "id", unique = true)
    private BookEntity bookID;

    public StockEntity()
    {
        this.bookID = new BookEntity();
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

    public String getBookIDasBookEntity()
    {
        return bookID.getId();
    }

    public void setBookID(BookEntity bookID)
    {
        this.bookID = bookID;
    }

    public void setBookID(String bookID)
    {
        this.bookID.setId(bookID);
    }

}
