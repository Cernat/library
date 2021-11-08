package org.internship.library.app.persistence.repository;

import org.internship.library.app.persistence.entity.BookEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for BookEntity
 */
@Repository
public interface BookJpaRepository extends PagingAndSortingRepository<BookEntity, String> {
    BookEntity findBookEntityByAuthor(String authorName);
}
