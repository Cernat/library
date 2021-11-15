package org.internship.library.app.persistence.repository;

import org.internship.library.api.Book;
import org.internship.library.app.persistence.entity.BookEntity;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Testing Class (@link BookRepositoryImpl)
 */
@ExtendWith(MockitoExtension.class)
class BookRepositoryImplTest {

    @Mock
    private BookSpringProvidedRepository bookSpringProvidedRepository;

    @InjectMocks
    private BookRepositoryImpl bookRepositoryImpl;

    @Test
    void shouldFindBookByIdTest() {
        // Implementeaza un builder design pattern pentru book
        // Create a test book
        BookEntity testBook = new BookEntity();

        final String testBookId = "3";
        testBook.setId(testBookId);
        testBook.setTitle("Razv");
        testBook.setAuthor("Cern");
        testBook.setNumberOfPages(50);
        BookEntity newBook = new BookEntity(testBook);

        when(bookSpringProvidedRepository.findById(testBookId)).thenReturn(Optional.of(testBook));
        Book foundBook = bookRepositoryImpl.findBookById(testBookId);

        // Test for every field
        // Test for Service, Controller
        assertEquals(testBook.getId(), foundBook.getId());
        assertEquals(testBook.getAuthor(), foundBook.getAuthor());
        assertEquals(testBook.getTitle(), foundBook.getTitle());
        assertEquals(testBook.getNumberOfPages(), foundBook.getNumberOfPages());

        verify(bookSpringProvidedRepository, times(1)).findById(testBookId);
    }

    @Test
     void canCreateBookTest() {
         BookEntity testBook = new BookEntity();
         testBook.setId("3");
         testBook.setTitle("Razv");
         testBook.setAuthor("Cern");
         testBook.setNumberOfPages(50);

        bookRepositoryImpl.createBook(testBook);

        ArgumentCaptor<BookEntity> bookEntityArgumentCaptor =
                ArgumentCaptor.forClass(BookEntity.class);

        verify(bookSpringProvidedRepository)
                .save(bookEntityArgumentCaptor.capture()); // We capture the value and verify if the Repo was invoked with same method

        BookEntity capturedBook = bookEntityArgumentCaptor.getValue();

//        assertThat(capturedBook).isEqualTo(testBook);
         assertEquals(testBook.getId(), capturedBook.getId());
         assertEquals(testBook.getAuthor(), capturedBook.getAuthor());
         assertEquals(testBook.getTitle(), capturedBook.getTitle());
         assertEquals(testBook.getNumberOfPages(), capturedBook.getNumberOfPages());

//        verify(bookSpringProvidedRepository, times(1)).save(testBook);

     }


    @Test
    void updateBook() {

        BookEntity testBook = new BookEntity();

        final String testBookId = "3";
        testBook.setId(testBookId);
        testBook.setTitle("Razv");
        testBook.setAuthor("Cern");
        testBook.setNumberOfPages(50);

        when(bookSpringProvidedRepository.findById(testBookId)).thenReturn(Optional.of(testBook));
        Book foundBook = bookRepositoryImpl.updateBook(testBookId, testBook);

        ArgumentCaptor<BookEntity> bookEntityArgumentCaptor =
                ArgumentCaptor.forClass(BookEntity.class);

        verify(bookSpringProvidedRepository)
                .save(bookEntityArgumentCaptor.capture()); // We capture the value and verify if the Repo was invoked with same method

        BookEntity capturedBook = bookEntityArgumentCaptor.getValue();

        System.out.println(capturedBook.getAuthor());

//        assertThat(capturedBook).isEqualTo(testBook);
        assertEquals(testBook.getId(), capturedBook.getId());
        assertEquals(testBook.getAuthor(), capturedBook.getAuthor());
        assertEquals(testBook.getTitle(), capturedBook.getTitle());
        assertEquals(testBook.getNumberOfPages(), capturedBook.getNumberOfPages());

//        verify(bookSpringProvidedRepository, times(1)).save(testBook);

    }

    @Test
//    @Disabled
    void canDeleteBookTest() {
        //given
        final String testBookId ="3";
//        given(bookSpringProvidedRepository.existsById(testBookId)).willReturn(true);

        //when
//        doNothing().when(bookSpringProvidedRepository.deleteById(testBookId));
        bookRepositoryImpl.deleteBook(testBookId);

        assertThat(bookSpringProvidedRepository.count()).isEqualTo(0); // Here should have been 1?
        //then
        verify(bookSpringProvidedRepository, times(1)).deleteById(testBookId);
    }

    @Test
    void findBookEntitiesByAuthorTest() {
        bookRepositoryImpl.findBookEntitiesByAuthor("Cern");
        verify(bookSpringProvidedRepository, times(1)).findBookEntitiesByAuthor("Cern");
    }
}