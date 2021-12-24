package org.internship.library.app.persistence.repository;

import java.util.Optional;

import org.internship.library.app.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer>
{
    Optional<UserEntity> findByUserName(String userName);

    Optional<UserEntity> findByEmail(String email);
}
