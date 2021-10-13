package org.internship.library.api;

/**
 * Book class
 */
public class Book {
    private Long id;
    private String title;
    private String author;
    private Integer numberOfPages;

    public Book(Long id, String title, String author, Integer numberOfPages) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.numberOfPages = numberOfPages;
    }

    public Book() {
    }

    public Book(String title, String author, Integer numberOfPages) {
        this.title = title;
        this.author = author;
        this.numberOfPages = numberOfPages;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(Integer numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    @Override
    public String toString() {
        return "Book{" +
                "Id=" + id +
                ", title='" + title + '\'' +
                ", Author='" + author + '\'' +
                ", numberOfPages=" + numberOfPages +
                '}';
    }
}
