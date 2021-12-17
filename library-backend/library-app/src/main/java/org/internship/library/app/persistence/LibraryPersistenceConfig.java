package org.internship.library.app.persistence;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.internship.library.api.BookAPI.Book;
import org.internship.library.app.persistence.entity.BookEntity;
import org.internship.library.impl.DTO.BookDTO;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class LibraryPersistenceConfig implements InitializingBean {

    @Autowired(required = false)
    private ObjectMapper objectMapper;

    @Override
    public void afterPropertiesSet() throws Exception {

        if (objectMapper != null) {

            final SimpleModule bookModule = new SimpleModule();
//            bookModule.addAbstractTypeMapping(Book.class, BookEntity.class);
            bookModule.addAbstractTypeMapping(Book.class, BookDTO.class);

            // Register the modules
            objectMapper.registerModules(bookModule);
        }
    }

}
