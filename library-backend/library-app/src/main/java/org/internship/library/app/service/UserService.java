package org.internship.library.app.service;

import org.internship.library.api.DTO.UserDTO;
import org.internship.library.app.adapter.UserMapper;
import org.internship.library.app.persistence.entity.UserEntity;
import org.internship.library.app.persistence.repository.UserRepository;
import org.internship.library.app.security.ApplicationPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final ApplicationPasswordEncoder applicationPasswordEncoder;

    public UserService(UserRepository userRepository, ApplicationPasswordEncoder applicationPasswordEncoder) {
        this.userRepository = userRepository;
        this.applicationPasswordEncoder = applicationPasswordEncoder;
    }

    public List<UserDTO> findAll() {
        List<UserEntity> allUsers = userRepository.findAll();
        return new ArrayList<UserDTO>(UserMapper.listOfUsersEntityToListOfUsersDTO(allUsers));
    }

    public UserDTO findById(Integer id) {
        Optional<UserEntity> optionalUserEntity = userRepository.findById(id);
        return UserMapper.userEntityToUserDTO(optionalUserEntity.get());
    }

    public UserDTO createUser(UserDTO user) {
        String encodedPassword = applicationPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        UserEntity newUser = UserMapper.userDTOtoUserEntity(user);
        UserDTO newUserDTO = UserMapper.userEntityToUserDTO(userRepository.save(newUser));
        return newUserDTO;
    }

    public UserDTO updateUser(Integer id, UserDTO user) {
        UserEntity newUser = UserMapper.userDTOtoUserEntity(user);
        newUser.setId(id);
        return UserMapper.userEntityToUserDTO(userRepository.save(newUser));
    }

    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }
}
