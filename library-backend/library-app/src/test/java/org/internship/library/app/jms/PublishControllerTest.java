package org.internship.library.app.jms;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.internship.library.api.dto.BookDTO;
import org.internship.library.app.jms.producer.PublishController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jms.core.JmsTemplate;

@ExtendWith(MockitoExtension.class)
class PublishControllerTest
{

    private static final String testBookId = "100";
    private static final String testBookAuthor = "RazvanTEST";
    private static final String testBookTitle = "CernatTEST";
    private static final Integer testBookNumberOfPages = 50;

    @Mock
    private JmsTemplate jmsTemplate;

    @InjectMocks
    private PublishController publishController;

    @Test
    void publishMessage()
    {

        BookDTO testBook = new BookDTO();
        testBook.setId(testBookId);
        testBook.setTitle(testBookTitle);
//        testBook.setAuthor(testBookAuthor);
        testBook.setNumberOfPages(testBookNumberOfPages);

        publishController.publishMessage(testBook);
        verify(jmsTemplate, times(1)).convertAndSend("publishBook", testBook);

    }
}