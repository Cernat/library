package org.internship.library.app.security;

import org.internship.library.app.persistence.entity.UserEntity;
import org.internship.library.app.service.exception.UserCredentialsException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.internship.library.app.security.PasswordValidationHelper.validatePassword;

@ExtendWith(MockitoExtension.class)
class PasswordValidationHelperTest {


    @Test
    void shouldValidatePasswordTest() {
        UserEntity emptyUser = new UserEntity();
        emptyUser.setPassword("");

        UserCredentialsException thrown = Assertions.assertThrows(UserCredentialsException.class, () -> {
            validatePassword(emptyUser);
        }, "PASSWORD IS EMPTY");
        Assertions.assertEquals("PASSWORD IS EMPTY", thrown.getMessage());

        emptyUser.setPassword("test<");
        UserCredentialsException thrown2 = Assertions.assertThrows(UserCredentialsException.class, () -> {
            validatePassword(emptyUser);
        }, "CONTAINS_INVALID_CHARACTER CONTAINS_INVALID_CHARACTER [';', '<', '>', '{', '}', '[', ']', '+', '=', '?', '&', ':', '\\','`']");
        Assertions.assertEquals("CONTAINS_INVALID_CHARACTER [';', '<', '>', '{', '}', '[', ']', '+', '=', '?', '&', ':', '\\','`']", thrown2.getMessage());

        emptyUser.setPassword("test");
        UserCredentialsException thrown3 = Assertions.assertThrows(UserCredentialsException.class, () -> {
            validatePassword(emptyUser);
        }, "Password must contain at least one digit [0-9]." +
                "Password must contain at least one lowercase Latin character [a-z]." +
                "Password must contain at least one uppercase Latin character [A-Z]." +
                "Password must contain at least one special character like ! @ # & ( )." +
                "Password must contain a length of at least 8 characters and a maximum of 20 characters.");

        Assertions.assertEquals("Password must contain at least one digit [0-9]." +
                "Password must contain at least one lowercase Latin character [a-z]." +
                "Password must contain at least one uppercase Latin character [A-Z]." +
                "Password must contain at least one special character like ! @ # & ( )." +
                "Password must contain a length of at least 8 characters and a maximum of 20 characters.", thrown3.getMessage());

        emptyUser.setPassword("Test123!");
        validatePassword(emptyUser);
    }
}