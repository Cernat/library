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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class MyUserDetailsService implements UserDetailsService {

    Logger logger = LoggerFactory.getLogger(MyUserDetailsService.class);

    @Autowired
    UserRepository userRepository;
    @Autowired
    ApplicationPasswordEncoder applicationPasswordEncoder;

    private final String PASSWORD_PATTERN =
            "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{4,20}$";
    private final String INVALID_CHARACTERS = "^[^<>'\"/;:+=&`%{}\\]\\[\\\\?]*$";

    private final Pattern invalidPattern = Pattern.compile(INVALID_CHARACTERS);
    private final Pattern pattern = Pattern.compile(PASSWORD_PATTERN);

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
           Optional<UserEntity> user = userRepository.findByUserName(userName);

           user.orElseThrow(() -> new UsernameNotFoundException("Not found: " + userName));

//           return user.map(MyUserDetails::new).get();
            return new MyUserDetails(user.get());
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

//        validatePassword(user);
        String encodedPassword = applicationPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);

        return user;
    }

    private boolean isValid(String password) {
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    private boolean invalidCharacters(String password) {
        Matcher matcher = invalidPattern.matcher(password);
        return matcher.matches();
    }

    private void validatePassword(UserEntity user) {

        if (user.getPassword().trim().isEmpty()) {
            throw new UserCredentialsException("PASSWORD IS EMPTY");
        }

        if (!invalidCharacters(user.getPassword())) {
            throw new UserCredentialsException(
                    "CONTAINS_INVALID_CHARACTER [';', '<', '>', '{', '}', '[', ']', '+', '=', '?', '&', ':', '\\','`']");
        }

        if (!isValid(user.getPassword())) {
            throw new UserCredentialsException("Password must contain at least one digit [0-9]." +
                    "Password must contain at least one lowercase Latin character [a-z]." +
                    "Password must contain at least one uppercase Latin character [A-Z]." +
                    "Password must contain at least one special character like ! @ # & ( )." +
                    "Password must contain a length of at least 8 characters and a maximum of 20 characters.");
        }
    }
}
