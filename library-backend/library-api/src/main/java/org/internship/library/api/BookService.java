package org.internship.library.api;

/**
 * This interface defines the behaviour of a service
 */
public interface BookService {

    Book getBook(String id);
    Book createBook(Book book);
    Book updateBook(Book book);
    Book deleteBook(String id);
}

