package org.internship.library.app.controller;

import static org.internship.library.app.controller.BookControllerIT.createHeaders;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.internship.library.api.dto.UserDTO;
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

/**
 * Integration Testing Class from Controller to DB
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserControllerIT extends LibraryAppConfigTest
{

    TestRestTemplate testRestTemplate = new TestRestTemplate();
    @Value("${library.user.client.url}")
    String url;
    UserDTO foundUser;

    /**
     * Verify if post request persist an user in the database
     */
    @Test
    @Order(1)
    void createUserTest()
    {
        UserDTO user = new UserDTO();
        user.setUserName(testUserName + "Unique");
        user.setEmail(testUserEmail);
        user.setPassword(testUserPassword);
        user.setUserRole(testUserRole);

        HttpEntity<UserDTO> requestHeader =
            new HttpEntity<>(user, createHeaders(testUserName, testUserPassword));
        ResponseEntity<UserDTO> responseEntity =
            testRestTemplate.exchange(url + "/", HttpMethod.POST, requestHeader, UserDTO.class);

        this.foundUser = responseEntity.getBody();
        if (foundUser != null)
        {
            assertEquals(user.getEmail(), foundUser.getEmail());
            assertEquals(user.getUserRole(), foundUser.getUserRole());
        }
    }

    /**
     * Verify if get request return an user from database
     */
    @Test
    @Order(2)
    void shouldGetUser()
    {
        HttpEntity<UserDTO> requestHeader = new HttpEntity<>(createHeaders(testUserName, testUserPassword));
        ResponseEntity<UserDTO> responseEntity =
            testRestTemplate.exchange(url + "/{id}", HttpMethod.GET, requestHeader, UserDTO.class, foundUser.getId());
        UserDTO user = responseEntity.getBody();
        if (user != null)
        {
            assertEquals(user.getId(), foundUser.getId());
            assertEquals(user.getEmail(), foundUser.getEmail());
            assertEquals(user.getPassword(), foundUser.getPassword());
            assertEquals(user.getUserRole(), foundUser.getUserRole());
        }
        assertNotNull(responseEntity);
        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    }

    /**
     * Verify if get request return all users from database
     */
    @Test
    @Order(3)
    void getAllUsers()
    {
        HttpEntity<UserDTO> requestHeader = new HttpEntity<>(createHeaders(testUserName, testUserPassword));
        ResponseEntity<UserDTO[]> responseEntity =
            testRestTemplate.exchange(url + "/", HttpMethod.GET, requestHeader, UserDTO[].class);
        assertNotNull(responseEntity);
        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    }

    /**
     * Verify if update request updates a user in the database
     */
    @Test
    @Order(4)
    void updateUser()
    {
        foundUser.setUserName("New Updated Username");

        HttpEntity<UserDTO> requestHeaderWithBody =
            new HttpEntity<>(foundUser, createHeaders(testUserName, testUserPassword));

        ResponseEntity<UserDTO> responseEntity =
            testRestTemplate.exchange(url + "/{id}", HttpMethod.PUT, requestHeaderWithBody,
                UserDTO.class, foundUser.getId());

        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);

        UserDTO newUpdatedUser = responseEntity.getBody();

        if (newUpdatedUser != null)
        {
            assertEquals(newUpdatedUser.getId(), foundUser.getId());
            assertEquals(newUpdatedUser.getEmail(), foundUser.getEmail());
            assertEquals(newUpdatedUser.getPassword(), foundUser.getPassword());
            assertEquals(newUpdatedUser.getUserRole(), foundUser.getUserRole());
        }
    }

    /**
     * Verify if delete request delete an user in database
     */
    @Test
    @Order(5)
    void deleteUser()
    {

        HttpEntity<UserDTO> requestHeader = new HttpEntity<>(createHeaders(testUserName, testUserPassword));
        testRestTemplate.exchange(url + "/{id}", HttpMethod.DELETE, requestHeader, UserDTO.class, foundUser.getId());
        ResponseEntity<UserDTO> responseEntity =
            testRestTemplate.exchange(url + "/{id}", HttpMethod.GET, requestHeader, UserDTO.class, foundUser.getId());
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }
}