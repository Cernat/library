package org.internship.library.app.controller;

import org.internship.library.api.Book;
import org.internship.library.api.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "book")
public class BookController {

    Logger logger = LoggerFactory.getLogger(BookController.class);

    @Autowired
    BookService bookService;


    @GetMapping("/{id}")
    public Book getBook(@PathVariable String id) {
       return bookService.getBook(id);
   }

   @PostMapping(value = "post")
    public Book createBook(@RequestBody Book payload) {
        return bookService.createBook(payload);
   }

   @PutMapping(value = "put/{id}")
    public Book updateBook(@PathVariable String id) {
        return bookService.updateBook(id);
   }

   @DeleteMapping(value = "delete/{id}")
    public Book deleteBook(@PathVariable String id) {
        return bookService.deleteBook(id);
   }

}
