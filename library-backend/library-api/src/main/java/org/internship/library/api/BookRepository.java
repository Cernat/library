package org.internship.library.api;

import java.util.List;

/**
 * This interface defines the behaviour of Book repository
 */
public interface BookRepository {

    /**
     * Finds the book by the given id
     * Returns EntityNotFoundException on the contrary
     * Returns Bad_Request(400) if Book fields are incomplete
     * @param id the book id
     */
    Book findBookById(String id);

    /**
     * Creates the given book
     *
     * @param book entity
     */
    Book createBook(Book book);

    /**
     * Updates the book by the given id with the given book
     * Returns EntityNotFoundException on the contrary
     * Returns Bad_Request(400) if Book fields are incomplete
     * @param id   the book id
     * @param book entity
     */
    Book updateBook(String id, Book book);

    /**
     * Deletes the book by the given id
     *
     * @param id the book id
     */
    void deleteBook(String id);

    List<Book> findBookEntitiesByAuthor(String author);
}
