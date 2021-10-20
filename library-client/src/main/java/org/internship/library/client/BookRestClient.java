package org.internship.library.client;

import org.internship.library.api.Book;
import org.internship.library.api.BookService;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import javax.swing.text.html.parser.Entity;
import java.util.Arrays;

public class BookRestClient implements BookService {

    private RestTemplate restTemplate;

    @Override
    public Book getBook(String id) {
        String url = "http://localhost:7777/library/book/" + id;
        Book book = restTemplate.getForObject(url, Book.class);
        return book;
    }

    @Override
    public Book createBook(Book book) {
        String url = "http://localhost:7777/library/book/create";
        Book bookTemplate = restTemplate.postForObject(url, book, Book.class);
        return bookTemplate;
    }

    @Override
    public Book updateBook(String id, Book book) {
        String url = "http://localhost:7777/library/book/" + id;

        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Book> requestUpdate = new HttpEntity<>(book, headers);

        ResponseEntity<Book> bookTemplate = restTemplate.exchange(url, HttpMethod.GET, requestUpdate, Book.class);
        return null;
    }

    @Override
    public Book deleteBook(String id) {
        String url = "http://localhost:7777/library/book/" + id;
        restTemplate.delete(url, Book.class);
        return null;
    }

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
}
