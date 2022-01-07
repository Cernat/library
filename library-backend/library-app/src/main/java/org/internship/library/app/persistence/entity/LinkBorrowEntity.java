package org.internship.library.app.persistence.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "link_borrow")
public class LinkBorrowEntity implements Serializable
{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private BookEntity book;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "borrow_id", unique = true)
    private BorrowEntity borrow;

    public LinkBorrowEntity()
    {
        this.user = new UserEntity();
        this.book = new BookEntity();
        this.borrow = new BorrowEntity();
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

    public Integer getUserIDasUserEntity()
    {
        return user.getId();
    }

    public void setUser(UserEntity user)
    {
        this.user = user;
    }

    public void setUser(Integer userID)
    {
        this.user.setId(userID);
    }

    public BookEntity getBook()
    {
        return book;
    }

    public String getBookIDasBookEntity()
    {
        return book.getId();
    }

    public void setBook(BookEntity book)
    {
        this.book = book;
    }

    public void setBook(String bookID)
    {
        this.book.setId(bookID);
    }

    public BorrowEntity getBorrow()
    {
        return borrow;
    }

    public Integer getBorrowIDasBorrowEntity()
    {
        return borrow.getId();
    }

    public void setBorrow(BorrowEntity borrow)
    {
        this.borrow = borrow;
    }

    public void setBorrow(Integer borrowID)
    {
        this.borrow.setId(borrowID);
    }

}
