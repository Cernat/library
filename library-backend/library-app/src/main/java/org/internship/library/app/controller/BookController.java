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

    /**
     * Receive id of a book
     * @param id Book requested from the client
     * @return Book
     */
    @GetMapping("/{id}")
    public Book getBook(@PathVariable String id) {
       return bookService.getBook(id);
   }

    /**
     * Receive a book entity to create
     * @param bookPayload The book's parameters to create
     * @return Response
     */
   @PostMapping()
    public Book createBook(@RequestBody Book bookPayload) {
        return bookService.createBook(bookPayload);
   }

    /**
     * Receive a book entity to update
     * @param bookPayload The book's parameters to update
     * @return Response
     */
   @PutMapping(value = "put")
    public Book updateBook(@RequestBody Book bookPayload) {
        return bookService.updateBook(bookPayload);
   }

    /**
     * Receive id of a book
     * @param id Book requested from the client to delete
     * @return Response
     */
   @DeleteMapping(value = "delete/{id}")
    public Book deleteBook(@PathVariable String id) {
        return bookService.deleteBook(id);
   }

}
