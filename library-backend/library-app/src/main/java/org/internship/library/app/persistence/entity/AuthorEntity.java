package org.internship.library.app.persistence.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "author")
public class AuthorEntity implements Serializable
{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String first_name;
    private String last_name;
    @OneToMany(mappedBy = "author", cascade = CascadeType.PERSIST)
    //    @JoinTable(name = "AUTHOR_BOOK", joinColumns =@JoinColumn(name = "AUTHOR_ID"),
    //                inverseJoinColumns = @JoinColumn(name = "BOOK_ID")
    //    )
    private List<BookEntity> books = new ArrayList<>();

    public AuthorEntity() {
    }

    public AuthorEntity(Integer id, String first_name, String last_name, List<BookEntity> books) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.books = books;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getFirst_name()
    {
        return first_name;
    }

    public void setFirst_name(String first_name)
    {
        this.first_name = first_name;
    }

    public String getLast_name()
    {
        return last_name;
    }

    public void setLast_name(String last_name)
    {
        this.last_name = last_name;
    }

    public List<BookEntity> getBooks()
    {
        return books;
    }

    public void setBooks(List<BookEntity> books)
    {
        this.books = books;
    }
}
