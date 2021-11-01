package org.internship.utility.app;

import org.internship.library.api.Book;
import org.internship.library.client.BookRestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UtilityController {

    @Autowired
    BookRestClient bookRestClient;

    /**
     * Retrieves a book
     * @return the book
     */
    @GetMapping("get-rest-library")
    public ResponseEntity<?> get() {
        return ResponseEntity.ok(bookRestClient.getBook(1));
    }

    /**
     * Retrieves the book created
     * @param bookPayload a book object
     * @return the book
     */
    @PostMapping("post-rest-library")
    public ResponseEntity<?> post(@RequestBody Book bookPayload) {
        return ResponseEntity.ok(bookRestClient.createBook(bookPayload));
    }

    /**
     * Retrieves the book updated
     * @param id the id of book that it will be updated
     * @param bookPayload the book object
     * @return the modified book
     */
    @PutMapping("put-rest-library/{id}")
    public ResponseEntity<?> put(@PathVariable String id, @RequestBody Book bookPayload) {
        return ResponseEntity.ok(bookRestClient.updateBook(id,bookPayload));
    }

    /**
     * Delete the book specified by the id
     * @param id the book id
     * @return the book
     */
    @PutMapping("delete-rest-library/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        return ResponseEntity.ok(bookRestClient.deleteBook(id));
    }

}
