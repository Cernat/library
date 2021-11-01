package org.internship.library.api;

public interface Book {
    public int getId();
    public void setId(String id);

    public String getTitle();
    public void setTitle(String title);

    public String getAuthor();
    public void setAuthor(String author);

    public Integer getNumberOfPages();
    public void setNumberOfPages(Integer numberOfPages);

}
