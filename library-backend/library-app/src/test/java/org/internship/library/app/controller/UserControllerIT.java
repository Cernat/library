package org.internship.library.app.controller;

import org.internship.library.api.dto.UserDTO;
import org.internship.library.app.LibraryAppConfigTest;
import org.internship.library.app.persistence.entity.UserEntity;
import org.internship.library.app.persistence.repository.UserRepository;
import org.internship.library.app.security.ApplicationPasswordEncoder;
import org.internship.library.app.security.UserRole;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.internship.library.app.controller.BookControllerIT.createHeaders;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Integration Testing Class from Controller to DB
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserControllerIT extends LibraryAppConfigTest {

    TestRestTemplate testRestTemplate = new TestRestTemplate();
    @Autowired
    UserRepository userRepository;
    @Value("${library.user.client.url}")
    String url;
    private static final String testUsername = "userTest";
    private static final String testPassword = "userTest123!";
    private static final String testEmail = "userTest@gmail.com";

    /**
     * Verify if post request persist an user in the database
     */
    @Test
    @Order(1)
    void createUserTest() {

        UserEntity userTest = new UserEntity();
        userTest.setUserName(testUsername);
        userTest.setPassword(testPassword);
        userTest.setEmail(testEmail);
        userTest.setUserRole(UserRole.USER);

        HttpEntity<UserEntity> requestHeader = new HttpEntity<>(userTest, createHeaders(testUserName, testUserPassword));
        ResponseEntity responseEntity = testRestTemplate.exchange(url + "/", HttpMethod.POST, requestHeader, UserEntity.class);
        assertNotNull(responseEntity);
        assertEquals(responseEntity.getStatusCode(), HttpStatus.CREATED);

    }

    /**
     * Verify if get request return an user from database
     */
    @Test
    @Order(2)
    void shouldGetUser() {

        HttpEntity<UserEntity> requestHeader = new HttpEntity<>(createHeaders(testUserName, testUserPassword));
        Optional<UserEntity> userToFind = userRepository.findByUserName(testUsername);

        ResponseEntity responseEntity = testRestTemplate.exchange(url + "/{id}", HttpMethod.GET, requestHeader, UserEntity.class, userToFind.get().getId());
        UserEntity foundUser = (UserEntity) responseEntity.getBody();
        assertEquals(userToFind.get().getUserName(), foundUser.getUserName());
        assertNotNull(responseEntity);
        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    }

    /**
     * Verify if get request return all users from database
     */
    @Test
    @Order(3)
    void getAllUsers() {

        HttpEntity<UserEntity> requestHeader = new HttpEntity<>(createHeaders(testUserName, testUserPassword));
        ResponseEntity responseEntity = testRestTemplate.exchange(url + "/", HttpMethod.GET, requestHeader, UserDTO[].class);
        assertNotNull(responseEntity);
        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    }

    /**
     * Verify if update request updates an user in the database
     */
    @Test
    @Order(4)
    void updateUser() {

        HttpEntity<UserEntity> requestHeader = new HttpEntity<>(createHeaders(testUserName, testUserPassword));
        Optional<UserEntity> foundUserBeforeUpdate = userRepository.findByUserName(testUsername);
        foundUserBeforeUpdate.get().setEmail("Updated Email");

        HttpEntity<UserEntity> requestHeaderWithBody = new HttpEntity<>(foundUserBeforeUpdate.get(), createHeaders(testUserName, testUserPassword));
        ResponseEntity responseEntity = testRestTemplate.exchange(url + "/{id}", HttpMethod.PUT, requestHeaderWithBody, UserEntity.class, foundUserBeforeUpdate.get().getId());
        assertNotNull(responseEntity);
        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);

        ResponseEntity foundBookAfterUpdate = testRestTemplate.exchange(url + "/{id}", HttpMethod.GET, requestHeader, UserEntity.class, foundUserBeforeUpdate.get().getId());
        assertNotNull(foundBookAfterUpdate);
        UserEntity userAfterUpdate = (UserEntity) foundBookAfterUpdate.getBody();
        assertEquals(foundUserBeforeUpdate.get().getEmail(), userAfterUpdate.getEmail());
    }

    /**
     * Verify if delete request delete an user in database
     */
    @Test
    @Order(5)
    void deleteUser() {

        HttpEntity<UserEntity> requestHeader = new HttpEntity<>(createHeaders(testUserName, testUserPassword));
        Optional<UserEntity> userToFind = userRepository.findByUserName(testUsername);

        ResponseEntity responseEntity = testRestTemplate.exchange(url + "/{id}", HttpMethod.DELETE, requestHeader, UserEntity.class, userToFind.get().getId());
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());

        ResponseEntity responseEntity2 = testRestTemplate.exchange(url + "/{id}", HttpMethod.GET, requestHeader, UserEntity.class, userToFind.get().getId());
        assertEquals(HttpStatus.NOT_FOUND, responseEntity2.getStatusCode());
    }
}