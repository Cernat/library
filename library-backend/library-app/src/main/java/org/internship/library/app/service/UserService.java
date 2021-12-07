package org.internship.library.app.service;

import org.internship.library.api.Book;
import org.internship.library.app.persistence.entity.BookEntity;
import org.internship.library.app.persistence.entity.UserEntity;
import org.internship.library.app.persistence.repository.UserRepository;
import org.internship.library.app.security.ApplicationPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    ApplicationPasswordEncoder applicationPasswordEncoder;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserEntity> findAll() {
        List<UserEntity> allUsers = userRepository.findAll();

        return new ArrayList<UserEntity>(allUsers);
    }

    public UserEntity findById(Integer id) {
        Optional<UserEntity> optionalUserEntity = userRepository.findById(id);
        return optionalUserEntity.get();
    }

    public UserEntity createUser(UserEntity user) {
        String encodedPassword = applicationPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        return userRepository.save(user);
    }

    public UserEntity updateUser(Integer id, UserEntity user) {
        UserEntity updatedUser = userRepository.findById(id).get();
        return userRepository.save(user);
    }

    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }
}
