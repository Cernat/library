package org.internship.library.client;

import org.internship.library.api.Book;
import org.internship.library.api.BookService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import javax.swing.text.html.parser.Entity;
import java.util.Arrays;

/**
 * Using RestTemplate, BookRestClient is a REST client for our API APP
 */
public class BookRestClient implements BookService {

    private RestTemplate restTemplate;

    @Value("${server.port}")
    private String port;

    /**
     * Retrieves the book specified by the id
     * @param id the book id
     * @return the book
     */
    @Override
    public Book getBook(String id) {
        String url = "http://localhost:" + port + "/library/book/" + id;
        Book book = restTemplate.getForObject(url, Book.class);
        return book;
    }

    /**
     * Retrieves the book created
     * @param book the book object
     * @return the book
     */
    @Override
    public Book createBook(Book book) {
        String url = "http://localhost:" + port + "/library/book/create";
        Book bookTemplate = restTemplate.postForObject(url, book, Book.class);
        return bookTemplate;
    }

    /**
     * Retrieves the book updated
     * @param id the id of book that it will be updated
     * @param book the book object
     * @return the modified book
     */
    @Override
    public Book updateBook(String id, Book book) {
        String url = "http://localhost:" + port + "/library/book/" + id;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Book> requestUpdate = new HttpEntity<>(book, headers);

        ResponseEntity<Book> bookEntity = restTemplate.exchange(url, HttpMethod.PUT, requestUpdate, Book.class);
        return bookEntity.getBody();
    }

    /**
     * Delete the book specified by the id
     * @param id the book id
     * @return the book
     */
    @Override
    public Book deleteBook(String id) {
        String url = "http://localhost:" + port + "/library/book/" + id;
        restTemplate.delete(url, Book.class);
        return null;
    }

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
}
