package org.internship.utility.app;

import org.internship.library.client.BookRestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UtilityController {

    @Autowired
    BookRestClient bookRestClient;

    @GetMapping("hello-rest-library")
    public ResponseEntity<?> method() {
        return ResponseEntity.ok(bookRestClient.getBook("1"));
//        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
