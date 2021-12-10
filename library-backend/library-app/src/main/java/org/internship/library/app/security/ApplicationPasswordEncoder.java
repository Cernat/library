package org.internship.library.app.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Extends the BCryptPasswordEncoder to have access to encode(), matches(), upgradeEncoding()
 * BCrypt it's a strong hashing function which encode the raw password
 */
@Component
public class ApplicationPasswordEncoder extends BCryptPasswordEncoder {

}
