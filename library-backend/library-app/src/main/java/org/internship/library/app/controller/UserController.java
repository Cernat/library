package org.internship.library.app.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.internship.library.api.dto.UserDTO;
import org.internship.library.app.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class UserController
{

    Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;

    public UserController(UserService userService)
    {
        this.userService = userService;
    }

    /**
     * Retrieves the user given by the id
     * 
     * @param id to search for
     * @return user DTO
     */
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Integer id)
    {
        logger.info("Retrieving user with the id of: " + id);

        try
        {
            return ResponseEntity.status(HttpStatus.OK).body(userService.findById(id));
        }
        catch (NoSuchElementException e)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    /**
     * Retrieves a list of users
     * 
     * @return list of all users
     */
    @GetMapping("/")
    public ResponseEntity<List<UserDTO>> getAllUsers()
    {
        logger.info("Retrieving all users: ");

        return ResponseEntity.ok(userService.findAll());
    }

    /**
     * Persist a user entity
     * 
     * @param userDTO user DTO
     * @return the user
     */
    @PostMapping("/")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO)
    {
        logger.info("Creating users with username: " + userDTO.getUserName());

        UserDTO newUser = userService.createUser(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

    /**
     * Update a user entity
     * 
     * @param id to search the user
     * @param userDTO newer field to update
     * @return the updated user entity
     */
    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Integer id, @RequestBody UserDTO userDTO)
    {
        logger.info("Updating users with username: " + userDTO.getUserName());

        return ResponseEntity.status(HttpStatus.OK).body(userService.updateUser(id, userDTO));
    }

    /**
     * Delete the user by the given id
     * 
     * @param id Search the user by the id
     * @return Http Response(204)
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer id)
    {
        logger.info("Deleting users with id: " + id);

        userService.deleteUser(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
