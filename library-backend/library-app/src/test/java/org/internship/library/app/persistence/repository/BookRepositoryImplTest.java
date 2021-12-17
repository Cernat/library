package org.internship.library.app.persistence.repository;

import org.internship.library.api.BookAPI.Book;
import org.internship.library.app.adapter.BookMapper;
import org.internship.library.app.persistence.entity.BookEntity;
import org.internship.library.impl.DTO.BookDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Unit Testing Class (@link BookRepositoryImpl)
 */
@ExtendWith(MockitoExtension.class)
class BookRepositoryImplTest {

    @Mock
    private BookSpringProvidedRepository bookSpringProvidedRepository;

    @InjectMocks
    private BookRepositoryImpl bookRepositoryImpl;

    /**
     * Verify if bookRepositoryImpl call the right method for findBookById
     */
    @Test
    void shouldFindBookByIdTest() {

        Book testBook = new BookDTO();
        final String testBookId = "3";
        testBook.setId(testBookId);
        testBook.setTitle("Razv");
        testBook.setAuthor("Cern");
        testBook.setNumberOfPages(50);

        BookEntity testBookEntity = BookMapper.bookDTOtoBookEntity((BookDTO) testBook);

        when(bookSpringProvidedRepository.findById(testBookId)).thenReturn(Optional.of(testBookEntity));
        Book foundBook = bookRepositoryImpl.findBookById(testBookId);

        assertEquals(testBook.getId(), foundBook.getId());
        assertEquals(testBook.getAuthor(), foundBook.getAuthor());
        assertEquals(testBook.getTitle(), foundBook.getTitle());
        assertEquals(testBook.getNumberOfPages(), foundBook.getNumberOfPages());
        verify(bookSpringProvidedRepository, times(1)).findById(testBookId);
    }

    /**
     * Verify if bookRepositoryImpl call the right method for createBook
     */
    @Test
    void canCreateBookTest() {
        Book testBook = new BookDTO();
        testBook.setId("3");
        testBook.setTitle("Razv");
        testBook.setAuthor("Cern");
        testBook.setNumberOfPages(50);

        BookEntity testBookEntity = BookMapper.bookDTOtoBookEntity((BookDTO) testBook);

        when(bookSpringProvidedRepository.save(Mockito.any(BookEntity.class))).thenReturn(testBookEntity);

        bookRepositoryImpl.createBook(testBook);
        ArgumentCaptor<BookEntity> bookDTOArgumentCaptor = ArgumentCaptor.forClass(BookEntity.class);

        verify(bookSpringProvidedRepository).save(bookDTOArgumentCaptor.capture());
        BookEntity capturedBook = bookDTOArgumentCaptor.getValue();

        assertEquals(testBook.getId(), capturedBook.getId());
        assertEquals(testBook.getAuthor(), capturedBook.getAuthor());
        assertEquals(testBook.getTitle(), capturedBook.getTitle());
        assertEquals(testBook.getNumberOfPages(), capturedBook.getNumberOfPages());
        verify(bookSpringProvidedRepository, times(1)).save(capturedBook);
    }

    /**
     * Verify if bookRepositoryImpl call the right method for updateBook
     */
    @Test
    void shouldUpdateBookTest() {

        Book testBook = new BookDTO();
        final String testBookId = "3";
        testBook.setId(testBookId);
        testBook.setTitle("Razv");
        testBook.setAuthor("Cern");
        testBook.setNumberOfPages(50);

        BookEntity testBookEntity = BookMapper.bookDTOtoBookEntity((BookDTO) testBook);

        when(bookSpringProvidedRepository.save(Mockito.any(BookEntity.class))).thenReturn(testBookEntity);

        bookRepositoryImpl.updateBook(testBookId, testBook);
        ArgumentCaptor<BookEntity> bookEntityArgumentCaptor = ArgumentCaptor.forClass(BookEntity.class);

        verify(bookSpringProvidedRepository).save(bookEntityArgumentCaptor.capture());
        BookEntity capturedBook = bookEntityArgumentCaptor.getValue();
        assertEquals(testBook.getId(), capturedBook.getId());
        assertEquals(testBook.getAuthor(), capturedBook.getAuthor());
        assertEquals(testBook.getTitle(), capturedBook.getTitle());
        assertEquals(testBook.getNumberOfPages(), capturedBook.getNumberOfPages());
        verify(bookSpringProvidedRepository, times(1)).save(capturedBook);
    }

    /**
     * Verify if bookRepositoryImpl call the right method for deleteBook
     */
    @Test
    void canDeleteBookTest() {
        final String testBookId = "3";
        bookRepositoryImpl.deleteBook(testBookId);
        assertThat(bookSpringProvidedRepository.count()).isEqualTo(0);
        verify(bookSpringProvidedRepository, times(1)).deleteById(testBookId);
    }

    /**
     * Verify if bookRepositoryImpl call the right method for findBookEntitiesByAuthor
     */
    @Test
    void findBookEntitiesByAuthorTest() {
        bookRepositoryImpl.findBookEntitiesByAuthor("Cern");
        verify(bookSpringProvidedRepository, times(1)).findBookEntitiesByAuthor("Cern");
    }
}