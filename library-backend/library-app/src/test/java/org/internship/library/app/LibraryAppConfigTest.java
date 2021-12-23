package org.internship.library.app;

import org.internship.library.api.dto.UserDTO;
import org.internship.library.app.adapter.UserMapper;
import org.internship.library.app.controller.UserController;
import org.internship.library.app.persistence.repository.UserRepository;
import org.internship.library.app.security.ApplicationPasswordEncoder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import org.springframework.test.context.event.annotation.BeforeTestExecution;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@EnableAutoConfiguration
@SpringBootTest(classes = { LibraryAppConfig.class }, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LibraryAppConfigTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ApplicationPasswordEncoder applicationPasswordEncoder;

    public static final Integer testUserId = 5;
    public static final String testUserName = "test";
    public static final String testUserPassword = "Test123!";
    public static final String testUserEmail = "userTest@gmail.com";
    public static final String testUserRole = "USER";

    @BeforeAll
    void beforeAll() {
        createUser();
    }

    public void createUser() {
        UserDTO adminUser = new UserDTO();
        adminUser.setId(testUserId);
        adminUser.setUserName(testUserName);
        adminUser.setPassword(applicationPasswordEncoder.encode(testUserPassword));
        adminUser.setEmail(testUserEmail);
        adminUser.setUserRole(testUserRole);

        if (!userRepository.findByUserName(testUserName).isPresent())
        {
            userRepository.save(UserMapper.userDTOtoUserEntity(adminUser));

        }

//        userController.createUser(adminUser);
    }
}
