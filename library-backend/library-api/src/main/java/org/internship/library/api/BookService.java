package org.internship.library.api;

import java.util.List;

/**
 * This interface defines the behaviour of a service
 */
public interface BookService {
    /**
     * Retrieving a book
     *
     * @param id the book id
     * @return the book
     */
    Book getBook(String id);

    /**
     * Creates the given book
     *
     * @param book the book object
     * @return the book
     */
    Book createBook(Book book);

    /**
     * Updates the book by the given id with the given book
     *
     * @param book the book object
     * @param id   the id of book that it will be updated
     * @return the book
     */
    Book updateBook(String id, Book book);

    /**
     * Deletes a book
     *
     * @param id the book id
     * @return the book
     */
    void deleteBook(String id);

    /**
     * Retrieves a list of books by given author
     * @param author to search books
     * @return a list of books
     */
    List<Book> findBookEntitiesByAuthor(String author);

    /**
     * Sets the BookRepository interface {@link BookRepository}
     * @param bookRepository interface
     */
    void setBookRepository(BookRepository bookRepository);

    /**
     * Gets the BookRepository interface {@link BookRepository}
     * @return instance of bookRepository
     */
    BookRepository getBookRepository();
}

