package org.internship.library.api.dto;

import java.util.Date;

public class BorrowDTO
{

    private Integer id;
    private Date date_borrowed;
    private Date to_be_returned;
    private boolean returned_on_time;
    private boolean returned;
    private LinkBorrowDTO linkBorrow;

    public BorrowDTO()
    {
    }

    public BorrowDTO(Integer id, Date date_borrowed, Date to_be_returned, boolean returned_on_time, boolean returned,
        LinkBorrowDTO linkBorrow)
    {
        this.id = id;
        this.date_borrowed = date_borrowed;
        this.to_be_returned = to_be_returned;
        this.returned_on_time = returned_on_time;
        this.returned = returned;
        this.linkBorrow = linkBorrow;
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

    public LinkBorrowDTO getLinkBorrow()
    {
        return linkBorrow;
    }

    public void setLinkBorrow(LinkBorrowDTO linkBorrow)
    {
        this.linkBorrow = linkBorrow;
    }
}
