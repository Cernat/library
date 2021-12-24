package org.internship.library.api.book;

import org.internship.library.api.dto.BookDTO;

import java.util.List;

/**
 * This interface defines the behaviour of Book repository
 */
public interface BookRepository
{

    /**
     * Finds the book by the given id Returns NoSuchElementException on the contrary
     *
     * @param id the book id
     */
    BookDTO findBookById(String id);

    /**
     * Creates the given book
     *
     * @param book entity
     */
    BookDTO createBook(BookDTO book);

    /**
     * Updates the book by the given id with the given book Returns NoSuchElementException on the contrary
     *
     * @param id the book id
     * @param book entity
     */
    BookDTO updateBook(String id, BookDTO book);

    /**
     * Deletes the book by the given id
     *
     * @param id the book id
     */
    void deleteBook(String id);

    /**
     * Retrieves all the books by the given author
     *
     * @param author to search books
     * @return book entities
     */
    List<BookDTO> findBookEntitiesByAuthor(String author);
}
