package org.internship.library.app.controller;

import org.internship.library.app.persistence.entity.UserEntity;
import org.internship.library.app.service.RegistrationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/registration")
public class RegistrationController
{

    private final RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService)
    {
        this.registrationService = registrationService;
    }

    /**
     * Sign up a new user
     *
     * @param user object to register
     * @return the user
     */
    @PostMapping
    public ResponseEntity<UserEntity> registerUser(@RequestBody UserEntity user)
    {
        UserEntity newUser = registrationService.register(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

    @RequestMapping("/login")
    public Principal user(Principal user)
    {
        return user;
    }
}
