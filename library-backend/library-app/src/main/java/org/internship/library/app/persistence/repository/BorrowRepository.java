package org.internship.library.app.persistence.repository;

import org.internship.library.app.persistence.entity.BorrowEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BorrowRepository extends JpaRepository<BorrowEntity, Integer>
{
}
