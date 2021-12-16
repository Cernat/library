package org.internship.library.app.service;

import org.internship.library.api.DTO.UserDTO;
import org.internship.library.app.adapter.UserMapper;
import org.internship.library.app.persistence.entity.UserEntity;
import org.internship.library.app.persistence.repository.UserRepository;
import org.internship.library.app.security.ApplicationPasswordEncoder;
import org.internship.library.app.security.UserRole;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Unit testing for {@link UserService}
 */
@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @Mock
    ApplicationPasswordEncoder applicationPasswordEncoder;

    @InjectMocks
    UserService userService;

    @BeforeEach
    void setUp() {

        UserEntity userTest = new UserEntity();
        userTest.setId(5);
        userTest.setUserName("userTest");
        userTest.setPassword("userTest");
        userTest.setEmail("userTest@gmail.com");
        userTest.setUserRole(UserRole.USER);
    }

    /**
     * Verify if userRepository call the right method for findAll
     */
    @Test
    void shouldFindAllUsersTest() {
        userService.findAll();
        verify(userRepository, times(1)).findAll();
    }

    /**
     * Verify if userRepository call the right method for findById
     */
    @Test
    void shouldFindUserByIdTest() {

        UserEntity testUser = new UserEntity();
        testUser.setId(5);
        testUser.setUserName("testUser");
        testUser.setPassword("testUser");
        testUser.setEmail("testUser@gmail.com");
        testUser.setUserRole(UserRole.USER);

        when(userRepository.findById(testUser.getId())).thenReturn(Optional.of(testUser));
        UserDTO foundUser = userService.findById(testUser.getId());

//        assertEquals(testUser.getId(), foundUser.getId());
        assertEquals(testUser.getUserName(), foundUser.getUserName());
        assertEquals(testUser.getPassword(), foundUser.getPassword());
        assertEquals(testUser.getEmail(), foundUser.getEmail());
        assertEquals(testUser.getUserRole(), UserRole.valueOf(foundUser.getUserRole()));
        verify(userRepository, times(1)).findById(testUser.getId());
    }

    /**
     * Verify if userRepository call the right method for save
     */
    @Test
    void canCreateUserTest() {

        UserEntity testUser = new UserEntity();
        testUser.setId(5);
        testUser.setUserName("testUser");
        testUser.setPassword("testUser");
        testUser.setEmail("testUser@gmail.com");
        testUser.setUserRole(UserRole.USER);

        UserDTO userDTO = UserMapper.userEntityToUserDTO(testUser);
        when(applicationPasswordEncoder.encode(testUser.getPassword())).thenReturn(testUser.getPassword());
        when(userRepository.save(Mockito.any(UserEntity.class))).thenAnswer(i -> i.getArguments()[0]);

        userService.createUser(userDTO);
        ArgumentCaptor<UserEntity> userEntityArgumentCaptor = ArgumentCaptor.forClass(UserEntity.class);
        verify(userRepository).save(userEntityArgumentCaptor.capture());
        UserEntity capturedUser = userEntityArgumentCaptor.getValue();

        assertEquals(testUser.getUserName(), capturedUser.getUserName());
        assertEquals(testUser.getPassword(), capturedUser.getPassword());
        assertEquals(testUser.getEmail(), capturedUser.getEmail());
        assertEquals(testUser.getUserRole(), capturedUser.getUserRole());
        verify(userRepository, times(1)).save(capturedUser);

    }

    /**
     * Verify if userRepository call the right method for save
     */
    @Test
    void updateUser() {

        UserEntity testUser = new UserEntity();
        testUser.setId(5);
        testUser.setUserName("testUser");
        testUser.setPassword("testUser");
        testUser.setEmail("testUser@gmail.com");
        testUser.setUserRole(UserRole.USER);

//        when(userRepository.findById(testUser.getId())).thenReturn(Optional.of(testUser));
        when(userRepository.save(Mockito.any(UserEntity.class))).thenAnswer(i -> i.getArguments()[0]);

        UserDTO userDTO = UserMapper.userEntityToUserDTO(testUser);
        userService.updateUser(testUser.getId(), userDTO);
        ArgumentCaptor<UserEntity> userEntityArgumentCaptor = ArgumentCaptor.forClass(UserEntity.class);

        verify(userRepository).save(userEntityArgumentCaptor.capture());
        UserEntity capturedUser = userEntityArgumentCaptor.getValue();
        assertEquals(testUser.getId(), capturedUser.getId());
        assertEquals(testUser.getUserName(), capturedUser.getUserName());
        assertEquals(testUser.getPassword(), capturedUser.getPassword());
        assertEquals(testUser.getEmail(), capturedUser.getEmail());
        assertEquals(testUser.getUserRole(), capturedUser.getUserRole());
        verify(userRepository, times(1)).save(capturedUser);
    }

    /**
     * Verify if userRepository call the right method for deleteById
     */
    @Test
    void deleteUser() {
        final Integer testUserId = 3;
        userRepository.deleteById(testUserId);
        assertThat(userRepository.count()).isEqualTo(0);
        verify(userRepository, times(1)).deleteById(testUserId);
    }
}