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
     * Retrieves the book specified by the id
     * @param id the book id
     * @return the book
     */
    @GetMapping("/{id}")
    public Book getBook(@PathVariable String id) {
       return bookService.getBook(id);
   }

    /**
     * Retrieves the book created
     * @param bookPayload a book object
     * @return the book
     */
   @PostMapping
    public Book createBook(@RequestBody Book bookPayload) {
        return bookService.createBook(bookPayload);
   }

    /**
     * Retrieves the book updated
     * @param bookPayload a book object
     * @return the book
     */
   @PutMapping("/{id}")
    public Book updateBook(@PathVariable String id, @RequestBody Book bookPayload) {
        return bookService.updateBook(id, bookPayload);
   }

    /**
     * Delete the book specified by the id
     * @param id the book id
     * @return the book
     */
   @DeleteMapping("/{id}")
    public Book deleteBook(@PathVariable String id) {
        return bookService.deleteBook(id);
   }

}
