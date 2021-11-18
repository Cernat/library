package org.internship.library.app.persistence.repository;

import org.internship.library.api.Book;
import org.internship.library.api.BookRepository;
import org.internship.library.app.persistence.entity.BookEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Repository implementation(@link BookRepository) using JPA spring(@link BookJpaRepository)
 */
@Repository
public class BookRepositoryImpl implements BookRepository {

    @Autowired
    private BookSpringProvidedRepository bookSpringProvidedRepository;

    @Override
    public Book findBookById(String id) {
        Optional<BookEntity> optionalBookEntity = bookSpringProvidedRepository.findById(id);
        return optionalBookEntity.get();
    }

    @Override
    public Book createBook(Book book) {
        BookEntity newBook =  bookSpringProvidedRepository.save(new BookEntity(book));
        return newBook;
    }

    @Override
    public Book updateBook(String id, Book book) {
        Book updatedBook = bookSpringProvidedRepository.findById(id).get();
        return bookSpringProvidedRepository.save(new BookEntity(book));
    }

    @Override
    public void deleteBook(String id) {
        Book book = bookSpringProvidedRepository.findById(id).get();
        bookSpringProvidedRepository.delete(new BookEntity(book));
    }

    public List<Book> findBookEntitiesByAuthor(String authorName) {
        List<BookEntity> bookEntities = bookSpringProvidedRepository.findBookEntitiesByAuthor(authorName);
        return new ArrayList<>(bookEntities);
    }

}
