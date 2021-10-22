package org.internship.library.app.controller;

import org.internship.library.api.Book;
import org.internship.library.api.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<Book> getBook(@PathVariable String id) {
        return ResponseEntity.status(HttpStatus.OK).body(bookService.getBook(id));
   }

    /**
     * Retrieves the book created
     * @param bookPayload a book object
     * @return the book
     */
    @PostMapping
    public ResponseEntity<Book> postBook(@RequestBody Book bookPayload) {
       Book book = bookService.createBook(bookPayload);
       return ResponseEntity.status(HttpStatus.CREATED)
               .body(book);
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
    public ResponseEntity<?> deleteBook(@PathVariable String id) {
       bookService.deleteBook(id);
       return new ResponseEntity(HttpStatus.NO_CONTENT);
   }

}
