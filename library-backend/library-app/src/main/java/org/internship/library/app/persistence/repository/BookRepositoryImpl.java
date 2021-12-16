package org.internship.library.app.persistence.repository;

import org.internship.library.api.BookAPI.Book;
import org.internship.library.api.BookAPI.BookRepository;
import org.internship.library.app.persistence.entity.BookEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * Repository implementation (@link BookRepository) using JPA spring (@link BookSpringProvidedRepository)
 */
@Repository
public class BookRepositoryImpl implements BookRepository {

    @Autowired
    private BookSpringProvidedRepository bookSpringProvidedRepository;

    @Override
    public Book findBookById(String id) {
        Optional<BookEntity> optionalBookEntity = bookSpringProvidedRepository.findById(id);
        return (Book) optionalBookEntity.get();
    }

    @Override
    public Book createBook(Book book) {
        return (Book) bookSpringProvidedRepository.save(new BookEntity(book));
    }

    @Override
    public Book updateBook(String id, Book book) {
        Book updatedBook = (Book) bookSpringProvidedRepository.findById(id).get();
        return (Book) bookSpringProvidedRepository.save(new BookEntity(book));
    }

    @Override
    public void deleteBook(String id) {
        bookSpringProvidedRepository.deleteById(id);
    }

    public List<Book> findBookEntitiesByAuthor(String authorName) {
        List<BookEntity> bookEntities = bookSpringProvidedRepository.findBookEntitiesByAuthor(authorName);
        return new ArrayList<Book>(bookEntities);
    }

}
