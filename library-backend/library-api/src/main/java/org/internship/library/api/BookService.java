package org.internship.library.api;

public interface BookService {

    Book getBook(String id);
    Book createBook(Book book);
    Book updateBook(String id);
    Book deleteBook(String id);
}

