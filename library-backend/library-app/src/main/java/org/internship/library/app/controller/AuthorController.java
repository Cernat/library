package org.internship.library.app.controller;

import org.internship.library.api.dto.AuthorDTO;
import org.internship.library.app.service.AuthorService;
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

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping(path = "author")
public class AuthorController {

    Logger logger = LoggerFactory.getLogger(AuthorController.class);
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }


    @GetMapping("/{id}")
    public ResponseEntity<AuthorDTO> getAuthor(@PathVariable Integer id)
    {
        logger.info("Retrieving author with the id of: " + id);

        try
        {
            return ResponseEntity.status(HttpStatus.OK).body(authorService.findById(id));
        }
        catch (NoSuchElementException e)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/")
    public ResponseEntity<List<AuthorDTO>> getAllAuthors()
    {
        logger.info("Retrieving all authors: ");

        return ResponseEntity.ok(authorService.findAll());
    }

    @PostMapping("/")
    public ResponseEntity<AuthorDTO> createAuthor(@RequestBody AuthorDTO authorDTO)
    {
        logger.info("Creating author with name: " + authorDTO.getFirst_name());

        AuthorDTO newAuthor = authorService.createAuthor(authorDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(newAuthor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuthorDTO> updateAuthor(@PathVariable Integer id, @RequestBody AuthorDTO authorDTO)
    {
        logger.info("Updating author with name: " + authorDTO.getFirst_name());

        return ResponseEntity.status(HttpStatus.OK).body(authorService.updateAuthor(id, authorDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAuthor(@PathVariable Integer id)
    {
        logger.info("Deleting author with id: " + id);

        authorService.deleteAuthor(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
