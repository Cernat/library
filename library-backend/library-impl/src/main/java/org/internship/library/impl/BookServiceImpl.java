package org.internship.library.impl;

import org.internship.library.api.Book;
import org.internship.library.api.BookService;

public class BookServiceImpl implements BookService {

    @Override
    public Book getBook(String id) {
        System.out.println("I will get a book here!");
        return null;
    }

    @Override
    public Book createBook(Book book) {
        System.out.println("I will create a book here!");
        return null;
    }

    @Override
    public Book updateBook(String id) {
        System.out.println("I will update a book here!");
        return null;
    }

    @Override
    public Book deleteBook(String id) {
        System.out.println("I will delete a book here, later! :D");
        return null;
    }
}
