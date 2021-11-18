package org.internship.library.app.controller;

import org.internship.library.app.LibraryAppConfigTest;
import org.internship.library.app.persistence.entity.BookEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Integration Testing Class from Controller to DB
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {LibraryAppConfigTest.class})
public class BookControllerIntegrationTest {

    TestRestTemplate testRestTemplate = new TestRestTemplate();
    HttpHeaders headers = new HttpHeaders();

    /**
     * Verify if get request return a book from database
     */
    @Test
    public void getBook() {

        String uri = "http://localhost:7777/library/book/{id}";
        final String testBookId = "5";
        ResponseEntity responseEntity = testRestTemplate.getForEntity(uri, BookEntity.class, testBookId);
        assertNotNull(responseEntity);
        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    }

    /**
     * Verify if post request persist a book in the database
     */
    @Test
    void postBook() {

        String uri = "http://localhost:7777/library/book";
        final String testBookId = "5";
        BookEntity testBook = new BookEntity();
        testBook.setId(testBookId);
        testBook.setTitle("Razv");
        testBook.setAuthor("Cern");
        testBook.setNumberOfPages(50);
        ResponseEntity responseEntity = testRestTemplate.postForEntity(uri, testBook, BookEntity.class);

        assertNotNull(responseEntity);
        assertEquals(responseEntity.getStatusCode(), HttpStatus.CREATED);
    }

    /**
     * Verify if update request update a book in the database
     */
    @Test
    void updateBook() {

        String uri = "http://localhost:7777/library/book/{id}";
        final String testBookId = "5";
        BookEntity testBook = new BookEntity();
        testBook.setId(testBookId);
        testBook.setTitle("Razv");
        testBook.setAuthor("Cern");
        testBook.setNumberOfPages(50);

        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<BookEntity> requestUpdate = new HttpEntity<>(testBook, headers);

        ResponseEntity responseEntity = testRestTemplate.exchange(uri, HttpMethod.PUT, requestUpdate, BookEntity.class, testBookId);
        assertNotNull(responseEntity);
        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    }

    /**
     * Verify if delete request delete a book in database
     */
    @Test
    void deleteBook() {

        String uri = "http://localhost:7777/library/book/{id}";
        final String testBookId = "5";
        testRestTemplate.delete(uri, testBookId);
        ResponseEntity responseEntity = testRestTemplate.getForEntity(uri, BookEntity.class, testBookId);
        assertEquals(responseEntity.getStatusCode(), HttpStatus.NOT_FOUND);
    }

    /**
     * Verify if get request return all books by author name
     */
    @Test
    void getBooksByAuthorName() {

        String authorName = "Cern";
        String uri = "http://localhost:7777/library/book/author?authorName=" + authorName;
        ResponseEntity<BookEntity[]> responseEntity = testRestTemplate.getForEntity(uri, BookEntity[].class);
        assertNotNull(responseEntity);
        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    }
}
