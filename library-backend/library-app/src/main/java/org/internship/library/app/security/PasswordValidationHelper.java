package org.internship.library.app.security;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.internship.library.app.persistence.entity.UserEntity;
import org.internship.library.app.service.exception.UserCredentialsException;

public class PasswordValidationHelper
{

    private static final String PASSWORD_PATTERN =
        "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–{}:;',?/*~$^+=<>]).{4,20}$";
    private static final String INVALID_CHARACTERS = "^[^<>'\"/;:+=&`%{}\\]\\[\\\\?]*$";

    private static final Pattern invalidPattern = Pattern.compile(INVALID_CHARACTERS);
    private static final Pattern pattern = Pattern.compile(PASSWORD_PATTERN);

    private static boolean isValid(String password)
    {
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    private static boolean invalidCharacters(String password)
    {
        Matcher matcher = invalidPattern.matcher(password);
        return matcher.matches();
    }

    public static void validatePassword(UserEntity user)
    {

        if (user.getPassword().trim().isEmpty())
        {
            throw new UserCredentialsException("PASSWORD IS EMPTY");
        }

        if (!invalidCharacters(user.getPassword()))
        {
            throw new UserCredentialsException(
                "CONTAINS_INVALID_CHARACTER [';', '<', '>', '{', '}', '[', ']', '+', '=', '?', '&', ':', '\\','`']");
        }

        if (!isValid(user.getPassword()))
        {
            throw new UserCredentialsException("Password must contain at least one digit [0-9]." +
                "Password must contain at least one lowercase Latin character [a-z]." +
                "Password must contain at least one uppercase Latin character [A-Z]." +
                "Password must contain at least one special character like ! @ # & ( )." +
                "Password must contain a length of at least 8 characters and a maximum of 20 characters.");
        }
    }
}
