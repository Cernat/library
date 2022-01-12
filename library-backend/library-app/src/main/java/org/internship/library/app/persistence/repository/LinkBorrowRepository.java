package org.internship.library.app.persistence.repository;

import org.internship.library.app.persistence.entity.LinkBorrowEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LinkBorrowRepository extends JpaRepository<LinkBorrowEntity, Integer>
{
}
