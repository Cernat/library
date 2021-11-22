package org.internship.library.app.persistence.repository;

import org.internship.library.api.Book;
import org.internship.library.app.persistence.entity.BookEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
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

        BookEntity testBook = new BookEntity();
        final String testBookId = "3";
        testBook.setId(testBookId);
        testBook.setTitle("Razv");
        testBook.setAuthor("Cern");
        testBook.setNumberOfPages(50);

        when(bookSpringProvidedRepository.findById(testBookId)).thenReturn(Optional.of(testBook));
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
        BookEntity testBook = new BookEntity();
        testBook.setId("3");
        testBook.setTitle("Razv");
        testBook.setAuthor("Cern");
        testBook.setNumberOfPages(50);

        bookRepositoryImpl.createBook(testBook);
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
     * Verify if bookRepositoryImpl call the right method for updateBook
     */
    @Test
    void updateBook() {

        BookEntity testBook = new BookEntity();
        final String testBookId = "3";
        testBook.setId(testBookId);
        testBook.setTitle("Razv");
        testBook.setAuthor("Cern");
        testBook.setNumberOfPages(50);
        when(bookSpringProvidedRepository.findById(testBookId)).thenReturn(Optional.of(testBook));
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