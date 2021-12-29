package org.internship.library.app.controller;

import org.internship.library.api.dto.LinkBorrowDTO;
import org.internship.library.app.service.LinkBorrowService;
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
@RequestMapping(path = "linkBorrow")
public class LinkBorrowController {

    Logger logger = LoggerFactory.getLogger(LinkBorrowController.class);

    private final LinkBorrowService linkBorrowService;

    public LinkBorrowController(LinkBorrowService linkBorrowService) {
        this.linkBorrowService = linkBorrowService;
    }


    @GetMapping("/{id}")
    public ResponseEntity<LinkBorrowDTO> getLinkBorrow(@PathVariable Integer id)
    {
        logger.info("Retrieving Linked Borrow with the id of: " + id);

        try
        {
            return ResponseEntity.status(HttpStatus.OK).body(linkBorrowService.findById(id));
        }
        catch (NoSuchElementException e)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/")
    public ResponseEntity<List<LinkBorrowDTO>> getAllLinkBorrow()
    {
        logger.info("Retrieving all linked borrows: ");

        return ResponseEntity.ok(linkBorrowService.findAll());
    }

    @PostMapping("/")
    public ResponseEntity<LinkBorrowDTO> createLinkBorrow(@RequestBody LinkBorrowDTO linkBorrowDTO)
    {
        logger.info("Creating link borrow with id: " + linkBorrowDTO.getId());

        LinkBorrowDTO newLinkBorrow = linkBorrowService.createLinkBorrow(linkBorrowDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(newLinkBorrow);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LinkBorrowDTO> updateLinkBorrow(@PathVariable Integer id, @RequestBody LinkBorrowDTO linkBorrowDTO)
    {
        logger.info("Updating link borrow with id: " + linkBorrowDTO.getId());

        return ResponseEntity.status(HttpStatus.OK).body(linkBorrowService.updateLinkBorrow(id, linkBorrowDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteLinkBorrow(@PathVariable Integer id)
    {
        logger.info("Deleting linked borrow with id: " + id);

        linkBorrowService.deleteLinkBorrow(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
