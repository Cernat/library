package org.internship.library.app.persistence.repository;

import org.internship.library.app.persistence.entity.StockEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<StockEntity, Integer>
{
}
