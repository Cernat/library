package org.internship.library.app.persistence.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.internship.library.api.book.BookRepository;
import org.internship.library.api.dto.BookDTO;
import org.internship.library.app.adapter.AuthorMapper;
import org.internship.library.app.adapter.BookMapper;
import org.internship.library.app.persistence.entity.AuthorEntity;
import org.internship.library.app.persistence.entity.BookEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Repository implementation (@link BookRepository) using JPA spring (@link BookSpringProvidedRepository)
 */
@Repository
public class BookRepositoryImpl implements BookRepository
{

    @Autowired
    private BookSpringProvidedRepository bookSpringProvidedRepository;

    @Override
    public BookDTO findBookById(String id)
    {
        Optional<BookEntity> optionalBookEntity = bookSpringProvidedRepository.findById(id);
        return BookMapper.bookEntityToBookDTO(optionalBookEntity.orElseThrow(NoSuchElementException::new));
    }

    @Override
    public BookDTO createBook(BookDTO book)
    {
        BookEntity bookEntity = BookMapper.bookDTOtoBookEntity(book);
        AuthorEntity author = AuthorMapper.authorDTOtoAuthorEntity(book.getAuthor());
        bookEntity.setAuthor(author);
        return BookMapper.bookEntityToBookDTO(bookSpringProvidedRepository.save(bookEntity));
    }

    @Override
    public BookDTO updateBook(String id, BookDTO book)
    {
        BookEntity bookEntity = BookMapper.bookDTOtoBookEntity(book);
        return BookMapper.bookEntityToBookDTO(bookSpringProvidedRepository.save(bookEntity));
    }

    @Override
    public void deleteBook(String id)
    {
        bookSpringProvidedRepository.deleteById(id);
    }

    @Override
    public List<BookDTO> findBookEntitiesByAuthor(String authorName)
    {
        List<BookEntity> bookEntities = bookSpringProvidedRepository.findBookEntitiesByAuthor(authorName);
        List<BookDTO> bookDTOS = BookMapper.listOfBooksEntityToListOfBookDTO(bookEntities);
        return new ArrayList<>(bookDTOS);
    }
}
