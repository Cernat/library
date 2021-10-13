package org.internship.library.api;

public class Book {
    private Long Id;
    private String title;
    private String Author;
    private Integer numberOfPages;

    public Book(Long id, String title, String author, Integer numberOfPages) {
        Id = id;
        this.title = title;
        Author = author;
        this.numberOfPages = numberOfPages;
    }

    public Book() {
    }

    public Book(String title, String author, Integer numberOfPages) {
        this.title = title;
        Author = author;
        this.numberOfPages = numberOfPages;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
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
                "Id=" + Id +
                ", title='" + title + '\'' +
                ", Author='" + Author + '\'' +
                ", numberOfPages=" + numberOfPages +
                '}';
    }
}
