package com.example.library.Book;

import org.springframework.stereotype.Service;

@Service
public class BookService {

    public Book getBooks() {
        return new Book((long) 1, "library Journey", "Razvan", 30);
    }
}
