package org.internship.library.api.book;

/**
 * This interface defines the structure of a Book
 */
public interface Book
{
    /**
     * Getter for id
     *
     * @return the id
     */
    String getId();

    /**
     * Setter for id
     *
     * @param id to set
     */
    void setId(String id);

    /**
     * Getter for title
     *
     * @return the title
     */
    String getTitle();

    /**
     * Setter for title
     *
     * @param title to set
     */
    void setTitle(String title);

    /**
     * Getter for author
     *
     * @return the author
     */
    String getAuthor();

    /**
     * Setter for author
     *
     * @param author to set
     */
    void setAuthor(String author);

    /**
     * Getter for numberOfPages
     *
     * @return the numberOfPages
     */
    Integer getNumberOfPages();

    /**
     * Setter for numberOfPages
     *
     * @param numberOfPages to set
     */
    void setNumberOfPages(Integer numberOfPages);

}
