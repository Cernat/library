package org.internship.library.api;

/**
 * This interface defines the structure of a Book
 */
public interface Book {
    /**
     * Setter and getter for id
     *
     * @return
     */
    public String getId();

    public void setId(String id);

    /**
     * Setter and getter for title
     *
     * @return
     */
    public String getTitle();

    public void setTitle(String title);

    /**
     * Setter and getter for author
     *
     * @return
     */
    public String getAuthor();

    public void setAuthor(String author);

    /**
     * Setter and getter for numberOfPages
     *
     * @return
     */
    public Integer getNumberOfPages();

    public void setNumberOfPages(Integer numberOfPages);

}
