package org.internship.library.api;

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
     * Creates the given
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

    void setBookRepository(BookRepository bookRepository);

    BookRepository getBookRepository();
}

