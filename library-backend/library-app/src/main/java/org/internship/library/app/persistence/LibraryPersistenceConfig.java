package org.internship.library.app.persistence;

import org.internship.library.api.book.Book;
import org.internship.library.api.dto.BookDTO;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class LibraryPersistenceConfig implements InitializingBean
{

    @Autowired(required = false)
    private ObjectMapper objectMapper;

    @Override
    public void afterPropertiesSet()
    {

        if (objectMapper != null)
        {

            final SimpleModule bookModule = new SimpleModule();
            bookModule.addAbstractTypeMapping(Book.class, BookDTO.class);

            // Register the modules
            objectMapper.registerModules(bookModule);
        }
    }

}
