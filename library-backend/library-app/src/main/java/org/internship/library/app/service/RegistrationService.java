package org.internship.library.app.service;

import org.internship.library.app.persistence.entity.UserEntity;
import org.internship.library.app.security.MyUserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService
{

    private final MyUserDetailsService myUserDetailsService;

    public RegistrationService(MyUserDetailsService myUserDetailsService)
    {
        this.myUserDetailsService = myUserDetailsService;
    }

    public UserEntity register(UserEntity user)
    {
        return myUserDetailsService.signUp(user);
    }
}
