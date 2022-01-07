package org.internship.library.app.controller;

import static org.internship.library.app.controller.BookControllerIT.createHeaders;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.internship.library.api.dto.AuthorDTO;
import org.internship.library.app.LibraryAppConfigTest;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AuthorControllerIT extends LibraryAppConfigTest
{

    TestRestTemplate testRestTemplate = new TestRestTemplate();
    @Value("${library.author.client.url}")
    String url;
    AuthorDTO foundAuthor;

    /**
     * Verify if post request persist an author in database
     */
    @Test
    @Order(1)
    void shouldPostAuthorTest()
    {
        AuthorDTO testAuthor = new AuthorDTO();
        testAuthor.setFirst_name(authorFirstNameTest);
        testAuthor.setLast_name(authorLastNameTest);

        HttpEntity<AuthorDTO> requestHeader =
            new HttpEntity<>(testAuthor, createHeaders(testUserName, testUserPassword));
        ResponseEntity<AuthorDTO> responseEntity =
            testRestTemplate.exchange(url + "/", HttpMethod.POST, requestHeader, AuthorDTO.class);

        this.foundAuthor = responseEntity.getBody();
        if (foundAuthor != null)
        {
            assertNotEquals(testAuthor.getId(), foundAuthor.getId());
            assertEquals(testAuthor.getFirst_name(), foundAuthor.getFirst_name());
            assertEquals(testAuthor.getLast_name(), foundAuthor.getLast_name());
        }
        assertNotNull(responseEntity);
    }

    /**
     * Verify if get request return a book from database
     */
    @Test
    @Order(2)
    public void shouldGetAuthorTest()
    {
        HttpEntity<AuthorDTO> requestHeader = new HttpEntity<>(createHeaders(testUserName, testUserPassword));
        ResponseEntity<AuthorDTO> responseEntity =
            testRestTemplate.exchange(url + "/{id}", HttpMethod.GET, requestHeader, AuthorDTO.class,
                foundAuthor.getId());
        AuthorDTO author = responseEntity.getBody();
        if (author != null)
        {
            assertEquals(foundAuthor.getId(), author.getId());
        }
        assertNotNull(responseEntity);
        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    }

    /**
     * Verify if get request return all books by author name
     */
    @Test
    @Order(3)
    void shouldGetAllAuthors()
    {
        HttpEntity<AuthorDTO> requestHeader = new HttpEntity<>(createHeaders(testUserName, testUserPassword));
        ResponseEntity<AuthorDTO[]> responseEntity = testRestTemplate.exchange(url + "/",
            HttpMethod.GET, requestHeader, AuthorDTO[].class);
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    /**
     * Verify if update request update a book in the database
     */
    @Test
    @Order(4)
    void shouldUpdateAuthorTest()
    {
        foundAuthor.setFirst_name("New Updated Author");

        HttpEntity<AuthorDTO> requestHeaderWithBody =
            new HttpEntity<>(foundAuthor, createHeaders(testUserName, testUserPassword));

        ResponseEntity<AuthorDTO> responseEntity =
            testRestTemplate.exchange(url + "/{id}", HttpMethod.PUT, requestHeaderWithBody,
                AuthorDTO.class, foundAuthor.getId());

        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);

        AuthorDTO newUpdatedAuthor = responseEntity.getBody();

        if (newUpdatedAuthor != null)
        {
            assertEquals(foundAuthor.getId(), newUpdatedAuthor.getId());
            assertEquals(foundAuthor.getFirst_name(), newUpdatedAuthor.getFirst_name());
            assertEquals(foundAuthor.getLast_name(), newUpdatedAuthor.getLast_name());
        }
    }

    /**
     * Verify if delete request delete a book in database
     */
    @Test
    @Order(5)
    void shouldDeleteAuthorTest()
    {
        HttpEntity<AuthorDTO> requestHeader = new HttpEntity<>(createHeaders(testUserName, testUserPassword));
        testRestTemplate.exchange(url + "/{id}", HttpMethod.DELETE, requestHeader, AuthorDTO.class,
            foundAuthor.getId());
        ResponseEntity<AuthorDTO> responseEntity =
            testRestTemplate.exchange(url + "/{id}", HttpMethod.GET, requestHeader, AuthorDTO.class,
                foundAuthor.getId());
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

}
