package org.internship.library.api;

/**
 * This interface defines the behaviour of Book repository
 */
public interface BookRepository {

    /**
     * Method without implementation to find a book
     * @param id the book id
     */
    Book findBookById(String id);

    /**
     * Method without implementation to create a book
     * @param book entity
     */
    Book createBook(Book book);

    /**
     * Method without implementation to update a book
     * @param id the book id
     * @param book entity
     */
    Book updateBook(String id, Book book);

    /**
     * Method without implementation to delete a book
     * @param id the book id
     */
    void deleteBook(String id);

}
