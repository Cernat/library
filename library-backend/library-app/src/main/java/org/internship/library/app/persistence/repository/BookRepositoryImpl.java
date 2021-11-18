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
//        Book foundBook = optionalBookEntity.get();
//        foundBook.setAuthor("Dl. " + foundBook.getAuthor());
//        return foundBook;
        return optionalBookEntity.orElseThrow(
                () -> new EntityNotFoundException("Book with id " + id + " not found"));
    }

    @Override
    public Book createBook(Book book) {
        BookEntity newBook =  bookSpringProvidedRepository.save(new BookEntity(book));
        return newBook;
    }

    @Override
    public Book updateBook(String id, Book book) {
        Book updatedBook = bookSpringProvidedRepository.findById(id).get(); // otherwise, Throws NoSuchElementException
        Optional<Book> optionalBookEntity = Optional.ofNullable(updatedBook);
        if (optionalBookEntity.isPresent()) {
            return bookSpringProvidedRepository.save(new BookEntity(book));
        } else {
            throw new EntityNotFoundException("Book with id " + id + " not found");
        }
    }

    //    @Query("delete from book t where t.id = ?1")
    @Override
    public void deleteBook(String id) {
//        Book book = bookSpringProvidedRepository.findById(id).get();
//        bookJpaRepository.delete(new BookEntity(book));
        bookSpringProvidedRepository.deleteById(id);
    }

    public List<Book> findBookEntitiesByAuthor(String authorName) {
        List<BookEntity> bookEntities = bookSpringProvidedRepository.findBookEntitiesByAuthor(authorName);
        return new ArrayList<>(bookEntities);
    }

}
