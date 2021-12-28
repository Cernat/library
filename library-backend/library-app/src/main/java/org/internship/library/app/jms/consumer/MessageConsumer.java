package org.internship.library.app.jms.consumer;

import org.internship.library.api.dto.BookDTO;
import org.internship.library.app.adapter.BookMapper;
import org.internship.library.app.persistence.repository.BookSpringProvidedRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class MessageConsumer
{

    private final static Logger LOGGER = LoggerFactory.getLogger(MessageConsumer.class);

    @Autowired
    BookSpringProvidedRepository bookSpringProvidedRepository;

    @JmsListener(destination = "publishBook")
    public void message(BookDTO bookDTO)
    {
        LOGGER.info("RECEIVED MESSAGE {}", bookDTO);

        bookSpringProvidedRepository.save(BookMapper.bookDTOtoBookEntity(bookDTO));
    }
}
