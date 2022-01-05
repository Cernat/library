package org.internship.library.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.internship.library.api.dto.UserDTO;
import org.internship.library.app.adapter.UserMapper;
import org.internship.library.app.persistence.entity.UserEntity;
import org.internship.library.app.persistence.repository.UserRepository;
import org.internship.library.app.security.ApplicationPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService
{

    private final UserRepository userRepository;
    private final ApplicationPasswordEncoder applicationPasswordEncoder;

    public UserService(UserRepository userRepository, ApplicationPasswordEncoder applicationPasswordEncoder)
    {
        this.userRepository = userRepository;
        this.applicationPasswordEncoder = applicationPasswordEncoder;
    }

    public List<UserDTO> findAll()
    {
        List<UserEntity> allUsers = userRepository.findAll();
        return new ArrayList<>(UserMapper.listOfUsersEntityToListOfUsersDTO(allUsers));
    }

    public UserDTO findById(Integer userId)
    {
        Optional<UserEntity> optionalUserEntity = userRepository.findById(userId);
        return UserMapper.userEntityToUserDTO(optionalUserEntity.orElseThrow(NoSuchElementException::new));
    }

    public UserDTO createUser(UserDTO user)
    {
        String encodedPassword = applicationPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        UserEntity newUser = UserMapper.userDTOtoUserEntity(user);
        return UserMapper.userEntityToUserDTO(userRepository.save(newUser));
    }

    public UserDTO updateUser(Integer userId, UserDTO user)
    {
        Optional<UserEntity> updateUser =
            Optional.of(userRepository.findById(userId).orElseThrow(NoSuchElementException::new));
        user.setPassword(updateUser.get().getPassword());
        return UserMapper.userEntityToUserDTO(userRepository.save(UserMapper.userDTOtoUserEntity(user)));
    }

    public void deleteUser(Integer userId)
    {
        userRepository.deleteById(userId);
    }
}
