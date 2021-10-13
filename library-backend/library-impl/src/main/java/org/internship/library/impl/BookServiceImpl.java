package org.internship.library.impl;

import org.internship.library.api.Book;
import org.internship.library.api.BookService;

public class BookServiceImpl implements BookService {

    @Override
    public Book getBook(String id) {
        System.out.println("I will create a book here!");
        return null;
    }
}
