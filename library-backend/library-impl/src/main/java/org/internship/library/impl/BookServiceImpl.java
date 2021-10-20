package org.internship.library.impl;

import org.internship.library.api.Book;
import org.internship.library.api.BookService;

public class BookServiceImpl implements BookService {

    /**
     * This method will return book
     * @param id The book id
     * @return The book found at this id
     */
    @Override
    public Book getBook(String id) {
        System.out.println("I will get a book here!");
        return new Book(1L, "Internship Journey", "Razvan", 90);
    }

    /**
     * This method will create book
     * @param book The book to create
     * @return The book created
     */
    @Override
    public Book createBook(Book book) {
        System.out.println("I will create a book here!");
        return book;
    }

    /**
     * This method will update book
     * @param book The book to update
     * @return The book updated
     */
    @Override
    public Book updateBook(String id, Book book) {
        System.out.println(book);
        System.out.println(id);
        System.out.println("I will update a book here!");
        return book;
    }

    /**
     * This method will delete book
     * @param id The book to delete
     * @return The book deleted
     */
    @Override
    public Book deleteBook(String id) {
        System.out.println("I will delete a book here, later! :D");
        return null;
    }
}
