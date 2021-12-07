package org.internship.library.app.controller;

import org.internship.library.app.persistence.entity.UserEntity;
import org.internship.library.app.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/{id}")
    public ResponseEntity<UserEntity> getUser(@PathVariable Integer id) {
        logger.info("Retrieving user with the id of: " + id);

        return ResponseEntity.ok(userService.findById(id));
    }

    @GetMapping("/")
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<List<UserEntity>> getAllUsers() {
        logger.info("Retrieving all users: ");

        return ResponseEntity.ok(userService.findAll());
    }

    @PostMapping("/")
    public ResponseEntity<UserEntity> createUser(@RequestBody UserEntity user) {
        logger.info("Creating users with username: " + user.getUserName());

        UserEntity newUser = userService.createUser(user);
        return ResponseEntity.ok(newUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserEntity> updateUser(@PathVariable Integer id, @RequestBody UserEntity user) {
        logger.info("Updating users with username: " + user.getUserName());

        return ResponseEntity.status(HttpStatus.OK).body(userService.updateUser(id, user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer id) {
        logger.info("Deleting users with id: " + id);

        userService.deleteUser(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
