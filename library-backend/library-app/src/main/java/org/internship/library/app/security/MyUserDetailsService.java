package org.internship.library.app.security;

import org.internship.library.app.persistence.entity.UserEntity;
import org.internship.library.app.persistence.repository.UserRepository;
import org.internship.library.app.service.exception.UserCredentialsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.internship.library.app.security.UserRole.USER;

@Service
public class MyUserDetailsService implements UserDetailsService {

    Logger logger = LoggerFactory.getLogger(MyUserDetailsService.class);

    @Autowired
    UserRepository userRepository;
    @Autowired
    ApplicationPasswordEncoder applicationPasswordEncoder;


    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

            UserEntity user = userRepository.findByUserName(userName).orElseThrow(() ->
                    new UsernameNotFoundException("Not found: " + userName));
            return new MyUserDetails(user);
    }

    public UserEntity signUp(UserEntity user) {
        logger.info("Sign Up the New User with the username: " + user.getUserName());

        Optional<UserEntity> userExists = userRepository.findByUserName(user.getUserName());

        if(userExists.isPresent()) {
            throw new UserCredentialsException("USERNAME EXISTS");
        }

        Optional<UserEntity> emailExists = userRepository.findByEmail(user.getEmail());

        if(emailExists.isPresent()) {
            throw new UserCredentialsException("EMAIL EXISTS");
        }

        PasswordValidationHelper.validatePassword(user);
        String encodedPassword = applicationPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
//        user.setUserRole(USER);
        userRepository.save(user);

        return user;
    }
}
