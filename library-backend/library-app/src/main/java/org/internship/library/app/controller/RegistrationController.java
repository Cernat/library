package org.internship.library.app.controller;

import org.internship.library.app.persistence.entity.UserEntity;
import org.internship.library.app.security.MyUserDetailsService;
import org.internship.library.app.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/registration")
public class RegistrationController {

    @Autowired
    RegistrationService registrationService;

    @PostMapping
    public ResponseEntity<UserEntity> registerUser(@RequestBody UserEntity user) {
        UserEntity newUser = registrationService.register(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

    @RequestMapping("/login")
    public Principal user(Principal user) {
        return user;
    }
}
