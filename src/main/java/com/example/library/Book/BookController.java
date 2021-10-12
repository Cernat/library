package com.example.library.Book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "book")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping(value = "get")
    public Book getBooks() {
        return bookService.getBooks();
}

   @RequestMapping(value = "post", method = RequestMethod.POST)
    public String post() {
       return "Post Method Book";
   }

   @PutMapping(value = "put")
    public String put() {
       return "Put Method Book";
   }

   @DeleteMapping(value = "delete")
    public String delete() {
       return "Detele Method Book";
   }
}
