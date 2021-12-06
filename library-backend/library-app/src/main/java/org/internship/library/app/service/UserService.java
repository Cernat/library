package org.internship.library.app.service;

import org.internship.library.api.Book;
import org.internship.library.app.persistence.entity.BookEntity;
import org.internship.library.app.persistence.entity.UserEntity;
import org.internship.library.app.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

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
