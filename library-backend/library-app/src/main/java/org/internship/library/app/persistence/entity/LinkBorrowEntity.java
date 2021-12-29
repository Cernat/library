package org.internship.library.app.persistence.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "LINKBORROW")
public class LinkBorrowEntity implements Serializable
{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_ID")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "book_ID")
    private BookEntity book;

    @OneToOne
    @JoinColumn(name = "borrow_ID")
    private BorrowEntity borrow;

    public LinkBorrowEntity()
    {
    }

    public LinkBorrowEntity(Integer id, UserEntity user, BookEntity book, BorrowEntity borrow)
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

    public UserEntity getUser()
    {
        return user;
    }

    public void setUser(UserEntity user)
    {
        this.user = user;
    }

    public BookEntity getBook()
    {
        return book;
    }

    public void setBook(BookEntity book)
    {
        this.book = book;
    }

    public BorrowEntity getBorrow()
    {
        return borrow;
    }

    public void setBorrow(BorrowEntity borrow)
    {
        this.borrow = borrow;
    }
}
