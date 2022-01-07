package org.internship.library.app.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Collections;

import org.internship.library.api.dto.BookDTO;
import org.internship.library.app.LibraryAppConfigTest;
import org.internship.library.app.adapter.AuthorMapper;
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
    BookDTO foundBook;

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
        BookDTO testBook = new BookDTO();
        testBook.setAuthor(AuthorMapper.authorEntityToAuthorDTO(authorEntity));
        testBook.setTitle(bookTitleTest);
        testBook.setNumberOfPages(bookNumberOfPagesTest);

        HttpEntity<BookDTO> requestHeader =
            new HttpEntity<>(testBook, createHeaders(testUserName, testUserPassword));
        ResponseEntity<BookDTO> responseEntity =
            testRestTemplate.exchange(url + "/", HttpMethod.POST, requestHeader, BookDTO.class);

        this.foundBook = responseEntity.getBody();
        if (foundBook != null)
        {
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
        HttpEntity<BookDTO> requestHeader = new HttpEntity<>(createHeaders(testUserName, testUserPassword));
        ResponseEntity<BookDTO> responseEntity =
            testRestTemplate.exchange(url + "/{id}", HttpMethod.GET, requestHeader, BookDTO.class, foundBook.getId());
        BookDTO book = responseEntity.getBody();
        if (book != null)
        {
            assertEquals(book.getId(), foundBook.getId());
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
        HttpEntity<BookDTO> requestHeader = new HttpEntity<>(createHeaders(testUserName, testUserPassword));
        ResponseEntity<BookDTO[]> responseEntity =
            testRestTemplate.exchange(url + "?authorName=" + foundBook.getAuthor().getFirst_name(),
                HttpMethod.GET, requestHeader, BookDTO[].class);
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
        foundBook.setTitle("New Updated Title");

        HttpEntity<BookDTO> requestHeaderWithBody =
            new HttpEntity<>(foundBook, createHeaders(testUserName, testUserPassword));

        ResponseEntity<BookDTO> responseEntity =
            testRestTemplate.exchange(url + "/{id}", HttpMethod.PUT, requestHeaderWithBody,
                BookDTO.class, foundBook.getId());

        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);

        BookDTO newUpdatedBook = responseEntity.getBody();

        if (newUpdatedBook != null)
        {
            assertEquals(foundBook.getId(), newUpdatedBook.getId());
            assertEquals(foundBook.getTitle(), newUpdatedBook.getTitle());
            assertEquals(foundBook.getNumberOfPages(), newUpdatedBook.getNumberOfPages());
        }
    }

    /**
     * Verify if delete request delete a book in database
     */
    @Test
    @Order(5)
    void shouldDeleteBookTest()
    {
        HttpEntity<BookDTO> requestHeader = new HttpEntity<>(createHeaders(testUserName, testUserPassword));
        testRestTemplate.exchange(url + "/{id}", HttpMethod.DELETE, requestHeader, BookDTO.class, foundBook.getId());
        ResponseEntity<BookDTO> responseEntity =
            testRestTemplate.exchange(url + "/{id}", HttpMethod.GET, requestHeader, BookDTO.class, foundBook.getId());
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }
}
