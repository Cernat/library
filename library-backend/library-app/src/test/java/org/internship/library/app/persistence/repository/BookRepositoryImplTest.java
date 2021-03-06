package org.internship.library.app.persistence.repository;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.internship.library.api.dto.BookDTO;
import org.internship.library.app.adapter.BookMapper;
import org.internship.library.app.persistence.entity.BookEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * Unit Testing Class (@link BookRepositoryImpl)
 */
@ExtendWith(MockitoExtension.class)
class BookRepositoryImplTest
{

    @Mock
    private BookSpringProvidedRepository bookSpringProvidedRepository;

    @InjectMocks
    private BookRepositoryImpl bookRepositoryImpl;

    private final static String testBookId = "3";
    private final static String testBookTitle = "Razv";
    private final static String testBookAuthor = "Cern";
    private final static Integer testNumberOfPages = 50;

    /**
     * Verify if bookRepositoryImpl call the right method for findBookById Can't mock static methods
     */
    //    @Test
    void shouldFindBookByIdTest()
    {
        BookDTO testBook = new BookDTO();
        testBook.setId(testBookId);
        testBook.setTitle(testBookTitle);
        testBook.setNumberOfPages(testNumberOfPages);

        BookEntity testBookEntity = BookMapper.bookDTOtoBookEntity(testBook);

        when(bookSpringProvidedRepository.findById(testBookId)).thenReturn(Optional.of(testBookEntity));
        BookDTO foundBook = bookRepositoryImpl.findBookById(testBookId);

        assertEquals(testBook.getId(), foundBook.getId());
        assertEquals(testBook.getTitle(), foundBook.getTitle());
        assertEquals(testBook.getNumberOfPages(), foundBook.getNumberOfPages());
        verify(bookSpringProvidedRepository, times(1)).findById(testBookId);
    }

    /**
     * Verify if bookRepositoryImpl call the right method for createBook Can't mock static methods
     */
    //    @Test
    void canCreateBookTest()
    {
        BookDTO testBook = new BookDTO();
        testBook.setId(testBookId);
        testBook.setTitle(testBookTitle);
        testBook.setNumberOfPages(testNumberOfPages);

        BookEntity testBookEntity = BookMapper.bookDTOtoBookEntity(testBook);

        when(bookSpringProvidedRepository.save(Mockito.any(BookEntity.class))).thenReturn(testBookEntity);

        bookRepositoryImpl.createBook(testBook);
        ArgumentCaptor<BookEntity> bookDTOArgumentCaptor = ArgumentCaptor.forClass(BookEntity.class);

        verify(bookSpringProvidedRepository).save(bookDTOArgumentCaptor.capture());
        BookEntity capturedBook = bookDTOArgumentCaptor.getValue();

        assertEquals(testBook.getId(), capturedBook.getId());
        assertEquals(testBook.getTitle(), capturedBook.getTitle());
        assertEquals(testBook.getNumberOfPages(), capturedBook.getNumberOfPages());
        verify(bookSpringProvidedRepository, times(1)).save(capturedBook);
    }

    /**
     * Verify if bookRepositoryImpl call the right method for updateBook Can't mock static methods
     */
    //    @Test
    void shouldUpdateBookTest()
    {
        BookDTO testBook = new BookDTO();
        testBook.setId(testBookId);
        testBook.setTitle(testBookTitle);
        testBook.setNumberOfPages(testNumberOfPages);

        BookEntity testBookEntity = BookMapper.bookDTOtoBookEntity(testBook);

        when(bookSpringProvidedRepository.save(Mockito.any(BookEntity.class))).thenReturn(testBookEntity);

        bookRepositoryImpl.updateBook(testBookId, testBook);
        ArgumentCaptor<BookEntity> bookEntityArgumentCaptor = ArgumentCaptor.forClass(BookEntity.class);

        verify(bookSpringProvidedRepository).save(bookEntityArgumentCaptor.capture());
        BookEntity capturedBook = bookEntityArgumentCaptor.getValue();
        assertEquals(testBook.getId(), capturedBook.getId());
        assertEquals(testBook.getTitle(), capturedBook.getTitle());
        assertEquals(testBook.getNumberOfPages(), capturedBook.getNumberOfPages());
        verify(bookSpringProvidedRepository, times(1)).save(capturedBook);
    }

    /**
     * Verify if bookRepositoryImpl call the right method for deleteBook
     */
    @Test
    void canDeleteBookTest()
    {
        bookRepositoryImpl.deleteBook(testBookId);
        assertThat(bookSpringProvidedRepository.count()).isEqualTo(0);
        verify(bookSpringProvidedRepository, times(1)).deleteById(testBookId);
    }

    /**
     * Verify if bookRepositoryImpl call the right method for findBookEntitiesByAuthor
     */
    @Test
    void findBookEntitiesByAuthorTest()
    {
        bookRepositoryImpl.findBookEntitiesByAuthor(testBookAuthor);
        verify(bookSpringProvidedRepository, times(1)).findBookEntitiesByTitle(testBookAuthor);
    }
}