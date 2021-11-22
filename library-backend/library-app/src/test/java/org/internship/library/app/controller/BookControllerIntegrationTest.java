package org.internship.library.app.controller;

import org.internship.library.app.LibraryAppConfigTest;
import org.internship.library.app.persistence.entity.BookEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Value;
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
    @Value("${library.book.client.url}")
    String url;
    final String testBookId = "5";
    final String authorName = "Cern";

    /**
     * Verify if get request return a book from database
     */
    @Test
    public void shouldGetBookTest() {

        ResponseEntity responseEntity = testRestTemplate.getForEntity(url + "/{id}", BookEntity.class, testBookId);
        BookEntity foundBook = (BookEntity) responseEntity.getBody();
        assertEquals(testBookId, foundBook.getId());
        assertNotNull(responseEntity);
        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    }

    /**
     * Verify if post request persist a book in the database
     */
    @Test
    void shouldPostBookTest() {

        BookEntity testBook = new BookEntity();
        testBook.setId(testBookId);
        testBook.setTitle("Razv");
        testBook.setAuthor("Cern");
        testBook.setNumberOfPages(50);
        ResponseEntity responseEntity = testRestTemplate.postForEntity(url + "/", testBook, BookEntity.class);
        BookEntity foundBook = (BookEntity) responseEntity.getBody();
        assertEquals(testBook.getId(), foundBook.getId());
        assertEquals(testBook.getAuthor(), foundBook.getAuthor());
        assertEquals(testBook.getTitle(), foundBook.getTitle());
        assertEquals(testBook.getNumberOfPages(), foundBook.getNumberOfPages());
        assertNotNull(responseEntity);
        assertEquals(responseEntity.getStatusCode(), HttpStatus.CREATED);
    }

    /**
     * Verify if update request update a book in the database
     */
    @Test
    void shouldUpdateBookTest() {

        ResponseEntity foundBookBeforeUpdate = testRestTemplate.getForEntity(url + "/{id}", BookEntity.class, testBookId);
        assertNotNull(foundBookBeforeUpdate);
        BookEntity bookBeforeUpdate = (BookEntity) foundBookBeforeUpdate.getBody();
        bookBeforeUpdate.setAuthor("Updated Author");

        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<BookEntity> requestUpdate = new HttpEntity<>(bookBeforeUpdate, headers);

        ResponseEntity responseEntity = testRestTemplate.exchange(url + "/{id}", HttpMethod.PUT, requestUpdate, BookEntity.class, testBookId);
        assertNotNull(responseEntity);
        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);

        ResponseEntity foundBookAfterUpdate = testRestTemplate.getForEntity(url + "/{id}", BookEntity.class, testBookId);
        assertNotNull(foundBookAfterUpdate);
        BookEntity bookAfterUpdate = (BookEntity) responseEntity.getBody();
        assertEquals(bookBeforeUpdate.getAuthor(), bookAfterUpdate.getAuthor());
    }

    /**
     * Verify if delete request delete a book in database
     */
    @Test
    void shouldDeleteBookTest() {

        testRestTemplate.delete(url + "/{id}", testBookId);
        ResponseEntity responseEntity = testRestTemplate.getForEntity(url + "/{id}", BookEntity.class, testBookId);
        assertEquals(responseEntity.getStatusCode(), HttpStatus.NOT_FOUND);
    }

    /**
     * Verify if get request return all books by author name
     */
    @Test
    void shouldGetBooksByAuthorNameTest() {

        ResponseEntity<BookEntity[]> responseEntity = testRestTemplate.getForEntity(url + "/?authorName=" + authorName, BookEntity[].class);
        assertNotNull(responseEntity);
        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    }
}
