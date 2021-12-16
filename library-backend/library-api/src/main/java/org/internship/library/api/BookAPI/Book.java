package org.internship.library.api.BookAPI;

/**
 * This interface defines the structure of a Book
 */
public interface Book {
    /**
     * Getter for id
     *
     * @return the id
     */
    public String getId();

    /**
     * Setter for id
     *
     * @param id to set
     */
    public void setId(String id);

    /**
     * Getter for title
     *
     * @return the title
     */
    public String getTitle();

    /**
     * Setter for title
     *
     * @param title to set
     */
    public void setTitle(String title);

    /**
     * Getter for author
     *
     * @return the author
     */
    public String getAuthor();

    /**
     * Setter for author
     *
     * @param author to set
     */
    public void setAuthor(String author);

    /**
     * Getter for numberOfPages
     *
     * @return the numberOfPages
     */
    public Integer getNumberOfPages();

    /**
     * Setter for numberOfPages
     *
     * @param numberOfPages to set
     */
    public void setNumberOfPages(Integer numberOfPages);

}
