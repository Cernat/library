package org.internship.library.api;

/**
 * This interface defines the behaviour of a service
 */
public interface BookService {
    /**
     * Method without implementation for retrieving a book
     * @param id the book id
     * @return the book
     */
    Book getBook(String id);

    /**
     * Method without implementation for creating a book
     * @param book the book object
     * @return the book
     */
    Book createBook(Book book);

    /**
     * Method without implementation for updating a book
     * @param book the book object
     * @return the book
     */
    Book updateBook(Book book);

    /**
     * Method without implementation for deleting a book
     * @param id the book id
     * @return the book
     */
    Book deleteBook(String id);
}

