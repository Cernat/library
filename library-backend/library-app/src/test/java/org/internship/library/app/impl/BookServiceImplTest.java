package org.internship.library.app.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.internship.library.api.book.BookRepository;
import org.internship.library.api.dto.BookDTO;
import org.internship.library.impl.BookServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * Unit Testing Class (@link BookServiceImpl)
 */
@ExtendWith(MockitoExtension.class)
public class BookServiceImplTest
{

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookServiceImpl bookServiceImpl;

    private final static String testBookId = "3";
    private final static String testBookTitle = "Razv";
    private final static String testBookAuthor = "Cern";
    private final static Integer testNumberOfPages = 50;

    /**
     * Verify if bookServiceImpl call the right method for getBook()
     */
    @Test
    void shouldGetBookTest()
    {
        BookDTO testBook = new BookDTO();

        testBook.setId(testBookId);
        testBook.setTitle(testBookTitle);
        testBook.setNumberOfPages(testNumberOfPages);

        when(bookRepository.findBookById(testBookId)).thenReturn(testBook);
        BookDTO foundBook = bookServiceImpl.getBook(testBookId);

        assertEquals(testBook.getId(), foundBook.getId());
        assertEquals(testBook.getTitle(), foundBook.getTitle());
        assertEquals(testBook.getNumberOfPages(), foundBook.getNumberOfPages());

        verify(bookRepository, times(1)).findBookById(testBookId);
    }

    /**
     * Verify if bookServiceImpl call the right method for createBook()
     */
    @Test
    void shouldCreateBookTest()
    {
        BookDTO testBook = new BookDTO();
        testBook.setId(testBookId);
        testBook.setTitle(testBookTitle);
        testBook.setNumberOfPages(testNumberOfPages);

        bookServiceImpl.createBook(testBook);

        ArgumentCaptor<BookDTO> bookDTOArgumentCaptor =
            ArgumentCaptor.forClass(BookDTO.class);

        verify(bookRepository)
            .createBook(bookDTOArgumentCaptor.capture());

        BookDTO capturedBook = bookDTOArgumentCaptor.getValue();

        assertEquals(testBook.getId(), capturedBook.getId());
        assertEquals(testBook.getTitle(), capturedBook.getTitle());
        assertEquals(testBook.getNumberOfPages(), capturedBook.getNumberOfPages());

        verify(bookRepository, times(1)).createBook(testBook);
    }

    /**
     * Verify if bookServiceImpl call the right method for updateBook()
     */
    @Test
    void shouldUpdateBookTest()
    {
        BookDTO testBook = new BookDTO();
        testBook.setId(testBookId);
        testBook.setTitle(testBookTitle);
        testBook.setNumberOfPages(testNumberOfPages);

        when(bookRepository.updateBook(eq(testBookId), any(BookDTO.class))).thenReturn(testBook);
        bookServiceImpl.updateBook(testBookId, testBook);

        ArgumentCaptor<BookDTO> bookDTOArgumentCaptor =
            ArgumentCaptor.forClass(BookDTO.class);

        verify(bookRepository)
            .updateBook(eq(testBookId), bookDTOArgumentCaptor.capture());

        BookDTO capturedBook = bookDTOArgumentCaptor.getValue();

        assertEquals(testBook.getId(), capturedBook.getId());
        assertEquals(testBook.getTitle(), capturedBook.getTitle());
        assertEquals(testBook.getNumberOfPages(), capturedBook.getNumberOfPages());

        verify(bookRepository, times(1)).updateBook(testBookId, testBook);
    }

    /**
     * Verify if bookServiceImpl call the right method for deleteBook()
     */
    @Test
    void shouldDeleteBookTest()
    {
        bookServiceImpl.deleteBook(testBookId);
        verify(bookRepository, times(1)).deleteBook(testBookId);
    }

    /**
     * Verify if bookServiceImpl call the right method for findBookEntitiesByAuthor()
     */
    @Test
    void shouldFindBookEntitiesByAuthor()
    {
        bookServiceImpl.findBookEntitiesByAuthor(testBookAuthor);
        verify(bookRepository, times(1)).findBookEntitiesByAuthor(testBookAuthor);
    }
}
