package org.internship.library.app.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Collections;

import org.internship.library.app.LibraryAppConfigTest;
import org.internship.library.app.persistence.entity.BookEntity;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

/**
 * Integration Testing Class from Controller to DB
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BookControllerIT extends LibraryAppConfigTest
{

    TestRestTemplate testRestTemplate = new TestRestTemplate();
    @Value("${library.book.client.url}")
    String url;
    private static final String testBookId = "100";
    private static final String testBookAuthor = "RazvanTEST";
    private static final String testBookTitle = "CernatTEST";
    private static final Integer testBookNumberOfPages = 50;

    public static HttpHeaders createHeaders(String username, String password)
    {
        return new HttpHeaders()
        {
            {
                String auth = username + ":" + password;
                byte[] encodedAuth = Base64.getEncoder().encode(
                    auth.getBytes(StandardCharsets.US_ASCII));
                String authHeader = "Basic " + new String(encodedAuth);
                set("Authorization", authHeader);
                setContentType(MediaType.APPLICATION_JSON);
                setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            }
        };
    }

    /**
     * Verify if post request persist a book in the database
     */
    @Test
    @Order(1)
    void shouldPostBookTest()
    {
        BookEntity testBook = new BookEntity();
        testBook.setId(testBookId);
        testBook.setTitle(testBookTitle);
        testBook.setAuthor(testBookAuthor);
        testBook.setNumberOfPages(testBookNumberOfPages);

        HttpEntity<BookEntity> requestHeader =
            new HttpEntity<>(testBook, createHeaders(testUserName, testUserPassword));
        ResponseEntity<BookEntity> responseEntity =
            testRestTemplate.exchange(url + "/", HttpMethod.POST, requestHeader, BookEntity.class);

        BookEntity foundBook = responseEntity.getBody();
        if (foundBook != null)
        {
            assertEquals(testBook.getId(), foundBook.getId());
            assertEquals(testBook.getAuthor(), foundBook.getAuthor());
            assertEquals(testBook.getTitle(), foundBook.getTitle());
            assertEquals(testBook.getNumberOfPages(), foundBook.getNumberOfPages());
        }
        assertNotNull(responseEntity);

    }

    /**
     * Verify if get request return a book from database
     */
    @Test
    @Order(2)
    public void shouldGetBookTest()
    {
        HttpEntity<BookEntity> requestHeader = new HttpEntity<>(createHeaders(testUserName, testUserPassword));
        ResponseEntity<BookEntity> responseEntity =
            testRestTemplate.exchange(url + "/{id}", HttpMethod.GET, requestHeader, BookEntity.class, testBookId);
        BookEntity foundBook = responseEntity.getBody();
        if (foundBook != null)
        {
            assertEquals(testBookId, foundBook.getId());
        }
        assertNotNull(responseEntity);
        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    }

    /**
     * Verify if get request return all books by author name
     */
    @Test
    @Order(3)
    void shouldGetBooksByAuthorNameTest()
    {
        HttpEntity<BookEntity> requestHeader = new HttpEntity<>(createHeaders(testUserName, testUserPassword));
        ResponseEntity<BookEntity[]> responseEntity = testRestTemplate.exchange(url + "/?authorName=" + testBookAuthor,
            HttpMethod.GET, requestHeader, BookEntity[].class);
        assertNotNull(responseEntity);
        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    }

    /**
     * Verify if update request update a book in the database
     */
    @Test
    @Order(4)
    void shouldUpdateBookTest()
    {
        HttpEntity<BookEntity> requestHeader = new HttpEntity<>(createHeaders(testUserName, testUserPassword));
        ResponseEntity<BookEntity> foundBookBeforeUpdate =
            testRestTemplate.exchange(url + "/{id}", HttpMethod.GET, requestHeader, BookEntity.class, testBookId);
        assertNotNull(foundBookBeforeUpdate);
        BookEntity bookBeforeUpdate = foundBookBeforeUpdate.getBody();
        if (bookBeforeUpdate != null)
        {
            bookBeforeUpdate.setAuthor("Updated Author");
        }

        HttpEntity<BookEntity> requestHeaderWithBody =
            new HttpEntity<>(bookBeforeUpdate, createHeaders(testUserName, testUserPassword));

        ResponseEntity<BookEntity> responseEntity =
            testRestTemplate.exchange(url + "/{id}", HttpMethod.PUT, requestHeaderWithBody,
                BookEntity.class, testBookId);
        assertNotNull(responseEntity);
        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);

        ResponseEntity<BookEntity> foundBookAfterUpdate =
            testRestTemplate.exchange(url + "/{id}", HttpMethod.GET, requestHeader, BookEntity.class, testBookId);
        assertNotNull(foundBookAfterUpdate);
        BookEntity bookAfterUpdate = responseEntity.getBody();
        if (bookBeforeUpdate != null && bookAfterUpdate != null)
        {
            assertEquals(bookBeforeUpdate.getAuthor(), bookAfterUpdate.getAuthor());
        }
    }

    /**
     * Verify if delete request delete a book in database
     */
    @Test
    @Order(5)
    void shouldDeleteBookTest()
    {
        HttpEntity<BookEntity> requestHeader = new HttpEntity<>(createHeaders(testUserName, testUserPassword));
        testRestTemplate.exchange(url + "/{id}", HttpMethod.DELETE, requestHeader, BookEntity.class, testBookId);
        ResponseEntity<BookEntity> responseEntity =
            testRestTemplate.exchange(url + "/{id}", HttpMethod.GET, requestHeader, BookEntity.class, testBookId);
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }
}
