package org.internship.library.app.persistence.repository;

import org.internship.library.api.BookAPI.Book;
import org.internship.library.api.BookAPI.BookRepository;
import org.internship.library.app.adapter.BookMapper;
import org.internship.library.app.persistence.entity.BookEntity;
import org.internship.library.impl.DTO.BookDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
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
        BookDTO bookDTO = BookMapper.bookEntityToBookDTO(optionalBookEntity.get());
        return bookDTO;
    }

    @Override
    public Book createBook(Book book) {
        BookDTO bookDTO = (BookDTO) book;
        BookEntity bookEntity = BookMapper.bookDTOtoBookEntity(bookDTO);
        return BookMapper.bookEntityToBookDTO(bookSpringProvidedRepository.save(bookEntity));
    }

    @Override
    public Book updateBook(String id, Book book) {
        BookDTO bookDTO = (BookDTO) book;
        BookEntity bookEntity = BookMapper.bookDTOtoBookEntity(bookDTO);
        return BookMapper.bookEntityToBookDTO(bookSpringProvidedRepository.save(bookEntity));

    }

    @Override
    public void deleteBook(String id) {
        bookSpringProvidedRepository.deleteById(id);
    }

    @Override
    public List<Book> findBookEntitiesByAuthor(String authorName) {
        List<BookEntity> bookEntities = bookSpringProvidedRepository.findBookEntitiesByAuthor(authorName);
        List<BookDTO> bookDTOS = BookMapper.listOfBooksEntityToListOfBookDTO(bookEntities);
        return new ArrayList<>(bookDTOS);
    }
}
