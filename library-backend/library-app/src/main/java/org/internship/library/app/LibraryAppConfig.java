package org.internship.library.app;

import javax.jms.ConnectionFactory;

import org.internship.library.api.book.BookRepository;
import org.internship.library.api.book.BookService;
import org.internship.library.impl.BookServiceImpl;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@ComponentScan(basePackages = {"org.internship.library.*"})
@EntityScan(basePackages = {"org.internship.library.app.persistence.entity"})
@EnableJpaRepositories(basePackages = "org.internship.library.*")
@EnableJms
public class LibraryAppConfig
{

    @Bean
    public BookService bookService(BookRepository bookRepository)
    {
        BookServiceImpl bookServiceImpl = new BookServiceImpl();
        bookServiceImpl.setBookRepository(bookRepository);
        return bookServiceImpl;
    }

    @Bean
    public Docket api()
    {
        return new Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.any())
            .paths(PathSelectors.any())
            .build();
    }

    @Bean
    public DefaultJmsListenerContainerFactory defaultJmsListenerContainerFactory(ConnectionFactory connectionFactory)
    {
        DefaultJmsListenerContainerFactory jmsListenerContainerFactory = new DefaultJmsListenerContainerFactory();

        jmsListenerContainerFactory.setConnectionFactory(connectionFactory);
        jmsListenerContainerFactory.setConcurrency("5-10");

        return jmsListenerContainerFactory;
    }
}
