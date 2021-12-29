package org.internship.library.app.persistence.repository;

import org.internship.library.app.persistence.entity.AuthorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<AuthorEntity, Integer>
{
}
