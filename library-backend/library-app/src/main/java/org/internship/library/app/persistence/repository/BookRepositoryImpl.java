package org.internship.library.app.persistence.repository;

import org.internship.library.api.Book;
import org.internship.library.api.BookRepository;
import org.internship.library.app.persistence.entity.BookEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

/**
 * Repository implementation(@link BookRepository) using JPA spring(@link BookJpaRepository)
 */
@Repository
public class BookRepositoryImpl implements BookRepository {

    @Autowired
    private BookJpaRepository bookJpaRepository;

    @Override
    public Book findBookById(String id) {
        Optional<BookEntity> optionalBookEntity = bookJpaRepository.findById(id);
        return optionalBookEntity.orElseThrow(
                () -> new EntityNotFoundException("Book with id " + id + " not found"));
    }

    @Override
    public Book createBook(Book book) {
        return bookJpaRepository.save(new BookEntity(book));
    }

    @Override
    public Book updateBook(String id, Book book) {
        Book updatedBook = bookJpaRepository.findById(id).get(); // otherwise, Throws NoSuchElementException
        Optional<Book> optionalBookEntity = Optional.ofNullable(updatedBook);
        if (optionalBookEntity.isPresent()) {
            return bookJpaRepository.save(new BookEntity(book));
        } else {
            throw new EntityNotFoundException("Book with id " + id + " not found");
        }
    }

    //    @Query("delete from book t where t.id = ?1")
    @Override
    public void deleteBook(String id) {
        Book book = bookJpaRepository.findById(id).get();
        bookJpaRepository.delete(new BookEntity(book));
    }
}
