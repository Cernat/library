package org.internship.library.app.persistence.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "borrow")
public class BorrowEntity implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "date_borrowed")
    private Date date_borrowed;

    @Column(name = "to_be_returned")
    private Date to_be_returned;

    @Column(name = "returned_on_time")
    private boolean returned_on_time;

    @Column(name = "returned")
    private boolean returned;

    public BorrowEntity()
    {
    }

    public BorrowEntity(Integer id, Date date_borrowed, Date to_be_returned, boolean returned_on_time, boolean returned)
    {
        this.id = id;
        this.date_borrowed = date_borrowed;
        this.to_be_returned = to_be_returned;
        this.returned_on_time = returned_on_time;
        this.returned = returned;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Date getDate_borrowed()
    {
        return date_borrowed;
    }

    public void setDate_borrowed(Date date_borrowed)
    {
        this.date_borrowed = date_borrowed;
    }

    public Date getTo_be_returned()
    {
        return to_be_returned;
    }

    public void setTo_be_returned(Date to_be_returned)
    {
        this.to_be_returned = to_be_returned;
    }

    public boolean isReturned_on_time()
    {
        return returned_on_time;
    }

    public void setReturned_on_time(boolean returned_on_time)
    {
        this.returned_on_time = returned_on_time;
    }

    public boolean isReturned()
    {
        return returned;
    }

    public void setReturned(boolean returned)
    {
        this.returned = returned;
    }

}
