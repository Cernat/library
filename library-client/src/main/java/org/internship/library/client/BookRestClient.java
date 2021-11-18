package org.internship.library.client;

import org.internship.library.api.Book;
import org.internship.library.api.BookRepository;
import org.internship.library.api.BookService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

/**
 * Using RestTemplate, BookRestClient is a REST client for our API APP
 */
public class BookRestClient implements BookService {

    private RestTemplate restTemplate;
    private String libraryBookPath;

    /**
     * Retrieves the book specified by the id
     *
     * @param id the book id
     * @return the book
     */
    @Override
    public Book getBook(String id) {
        String url = libraryBookPath + id;
        Book book = restTemplate.getForObject(url, Book.class);
        return book;
    }

    /**
     * Retrieves the book created
     *
     * @param book the book object
     * @return the book
     */
    @Override
    public Book createBook(Book book) {
        return restTemplate.postForObject(libraryBookPath, book, Book.class);
    }

    /**
     * Retrieves the book updated
     *
     * @param id   the id of book that it will be updated
     * @param book the book object
     * @return the modified book
     */
    @Override
    public Book updateBook(String id, Book book) {
        String url = libraryBookPath + id;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Book> requestUpdate = new HttpEntity<>(book, headers);

        ResponseEntity<Book> bookEntity = restTemplate.exchange(url, HttpMethod.PUT, requestUpdate, Book.class);
        return bookEntity.getBody();
    }

    /**
     * Delete the book specified by the id
     *
     * @param id the book id
     * @return the book
     */
    @Override
    public void deleteBook(String id) {
        String url = libraryBookPath + id;
        restTemplate.delete(url, Book.class);
    }

    @Override
    public List<Book> findBookEntitiesByAuthor(String author) {
        return null;
    }

    @Override
    public void setBookRepository(BookRepository bookRepository) {
        throw new UnsupportedOperationException("No repository required for the client");
    }

    @Override
    public BookRepository getBookRepository() {
        throw new UnsupportedOperationException("No repository required for the client");
    }

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void setLibraryBookPath(String libraryBookPath) {
        this.libraryBookPath = libraryBookPath;
    }
}
