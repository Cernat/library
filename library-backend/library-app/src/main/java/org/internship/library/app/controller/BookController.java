package org.internship.library.app.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.internship.library.api.book.BookService;
import org.internship.library.api.dto.BookDTO;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "book")
public class BookController
{

    Logger logger = LoggerFactory.getLogger(BookController.class);

    @Autowired
    BookService bookService;

    /**
     * Retrieves the book specified by the id
     *
     * @param id the book id
     * @return the book OR NOT_FOUND(404)
     */
    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getBook(@PathVariable String id)
    {
        logger.info("Retrieving book with the id of: " + id);

        try
        {
            return ResponseEntity.status(HttpStatus.OK).body(bookService.getBook(id));
        }
        catch (NoSuchElementException e)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    /**
     * Retrieves the book created
     *
     * @param bookPayload a book object
     * @return the book or BAD_REQUEST(400)
     */
    @PostMapping
    public ResponseEntity<BookDTO> postBook(@RequestBody BookDTO bookPayload)
    {
        logger.info("Receiving book from client: {}", bookPayload);

        BookDTO newBook = bookService.createBook(bookPayload);
        return ResponseEntity.status(HttpStatus.CREATED).body(newBook);
    }

    /**
     * Retrieves the book updated
     *
     * @param bookPayload a book object
     * @return the book
     */
    @PutMapping("/{id}")
    public ResponseEntity<BookDTO> updateBook(@PathVariable String id, @RequestBody BookDTO bookPayload)
    {
        logger.info("Update the book with the id of: " + id + " with: " + bookPayload);

        try
        {
            return ResponseEntity.status(HttpStatus.OK).body(bookService.updateBook(id, bookPayload));
        }
        catch (NoSuchElementException e)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    /**
     * Delete the book specified by the id
     *
     * @param id the book id
     * @return the book
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable String id)
    {
        logger.info("Deleting the book with the id of: " + id);
        bookService.deleteBook(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    /**
     * Retrieves a list of books specified by the author
     * 
     * @param authorName to search books
     * @return a list of book entities
     */
    @GetMapping
    public ResponseEntity<List<BookDTO>> getBooksByAuthorName(@RequestParam String authorName)
    {
        logger.info("Retrieving all books with author name: " + authorName);

        List<BookDTO> allBooksByAuthorName = bookService.findBookEntitiesByAuthor(authorName);
        return new ResponseEntity<>(allBooksByAuthorName, HttpStatus.OK);
    }
}
