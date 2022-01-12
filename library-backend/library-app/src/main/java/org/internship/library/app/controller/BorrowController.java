package org.internship.library.app.controller;

import org.internship.library.api.dto.BorrowDTO;
import org.internship.library.app.service.BorrowService;
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
@RequestMapping(path = "borrow")
public class BorrowController {

    Logger logger = LoggerFactory.getLogger(BorrowController.class);
    private final BorrowService borrowService;

    public BorrowController(BorrowService borrowService) {
        this.borrowService = borrowService;
    }


    @GetMapping("/{id}")
    public ResponseEntity<BorrowDTO> getBorrow(@PathVariable Integer id)
    {
        logger.info("Retrieving borrow with the id of: " + id);

        try
        {
            return ResponseEntity.status(HttpStatus.OK).body(borrowService.findById(id));
        }
        catch (NoSuchElementException e)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/")
    public ResponseEntity<List<BorrowDTO>> getAllBorrows()
    {
        logger.info("Retrieving all borrows: ");

        return ResponseEntity.ok(borrowService.findAll());
    }

    @PostMapping("/")
    public ResponseEntity<BorrowDTO> createBorrow(@RequestBody BorrowDTO borrowDTO)
    {
        logger.info("Creating borrow with on: " + borrowDTO.getDate_borrowed());

        BorrowDTO newBorrow = borrowService.createBorrow(borrowDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(newBorrow);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BorrowDTO> updateBorrow(@PathVariable Integer id, @RequestBody BorrowDTO borrowDTO)
    {
        logger.info("Updating borrow with date: " + borrowDTO.getDate_borrowed());

        return ResponseEntity.status(HttpStatus.OK).body(borrowService.updateBorrow(id, borrowDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBorrow(@PathVariable Integer id)
    {
        logger.info("Deleting borrow with id: " + id);

        borrowService.deleteBorrow(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
