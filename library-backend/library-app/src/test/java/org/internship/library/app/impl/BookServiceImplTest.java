package org.internship.library.app.impl;

import org.internship.library.api.BookAPI.Book;
import org.internship.library.api.BookAPI.BookRepository;
import org.internship.library.app.adapter.BookMapper;
import org.internship.library.app.persistence.entity.BookEntity;
import org.internship.library.impl.BookServiceImpl;
import org.internship.library.impl.DTO.BookDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Unit Testing Class (@link BookServiceImpl)
 */
@ExtendWith(MockitoExtension.class)
public class BookServiceImplTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookServiceImpl bookServiceImpl;

    /**
     * Verify if bookServiceImpl call the right method for getBook()
     */
    @Test
    void shouldGetBookTest() {

        Book testBook = new BookDTO();

        final String testBookId = "3";
        testBook.setId(testBookId);
        testBook.setTitle("Razv");
        testBook.setAuthor("Cern");
        testBook.setNumberOfPages(50);

        when(bookRepository.findBookById(testBookId)).thenReturn(testBook);
        Book foundBook = bookServiceImpl.getBook(testBookId);

        assertEquals(testBook.getId(), foundBook.getId());
        assertEquals(testBook.getAuthor(), foundBook.getAuthor());
        assertEquals(testBook.getTitle(), foundBook.getTitle());
        assertEquals(testBook.getNumberOfPages(), foundBook.getNumberOfPages());

        verify(bookRepository, times(1)).findBookById(testBookId);
    }

    /**
     * Verify if bookServiceImpl call the right method for createBook()
     */
    @Test
    void shouldCreateBookTest() {
        Book testBook = new BookDTO();
        testBook.setId("3");
        testBook.setTitle("Razv");
        testBook.setAuthor("Cern");
        testBook.setNumberOfPages(50);

        bookServiceImpl.createBook(testBook);

        ArgumentCaptor<BookDTO> bookDTOArgumentCaptor =
                ArgumentCaptor.forClass(BookDTO.class);

        verify(bookRepository)
                .createBook(bookDTOArgumentCaptor.capture());

        BookEntity capturedBook = BookMapper.bookDTOtoBookEntity(bookDTOArgumentCaptor.getValue());

//        assertThat(capturedBook).isEqualTo(testBook);
        assertEquals(testBook.getId(), capturedBook.getId());
        assertEquals(testBook.getAuthor(), capturedBook.getAuthor());
        assertEquals(testBook.getTitle(), capturedBook.getTitle());
        assertEquals(testBook.getNumberOfPages(), capturedBook.getNumberOfPages());

        verify(bookRepository, times(1)).createBook(testBook);
    }

    /**
     * Verify if bookServiceImpl call the right method for updateBook()
     */
    @Test
    void shouldUpdateBookTest() {
        Book testBook = new BookDTO();
        final String testBookId = "3";
        testBook.setId(testBookId);
        testBook.setTitle("Razv");
        testBook.setAuthor("Cern");
        testBook.setNumberOfPages(50);

        when(bookRepository.updateBook(eq(testBookId), any(BookDTO.class))).thenReturn(testBook);
        bookServiceImpl.updateBook(testBookId, testBook);

        ArgumentCaptor<BookDTO> bookDTOArgumentCaptor =
                ArgumentCaptor.forClass(BookDTO.class);

        verify(bookRepository)
                .updateBook(eq(testBookId), bookDTOArgumentCaptor.capture());

        BookEntity capturedBook = BookMapper.bookDTOtoBookEntity(bookDTOArgumentCaptor.getValue());

//        assertThat(capturedBook).isEqualTo(testBook);
        assertEquals(testBook.getId(), capturedBook.getId());
        assertEquals(testBook.getAuthor(), capturedBook.getAuthor());
        assertEquals(testBook.getTitle(), capturedBook.getTitle());
        assertEquals(testBook.getNumberOfPages(), capturedBook.getNumberOfPages());

        verify(bookRepository, times(1)).updateBook(testBookId, testBook);
    }

    /**
     * Verify if bookServiceImpl call the right method for deleteBook()
     */
    @Test
    void shouldDeleteBookTest() {
        final String testBookId = "3";
        bookServiceImpl.deleteBook(testBookId);
        verify(bookRepository, times(1)).deleteBook(testBookId);
    }

    /**
     * Verify if bookServiceImpl call the right method for findBookEntitiesByAuthor()
     */
    @Test
    void shouldFindBookEntitiesByAuthor() {
        final String author = "Cern";
        bookServiceImpl.findBookEntitiesByAuthor(author);
        verify(bookRepository, times(1)).findBookEntitiesByAuthor(author);
    }
}
