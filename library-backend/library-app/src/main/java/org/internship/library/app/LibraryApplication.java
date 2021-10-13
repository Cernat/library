package org.internship.library.app;

import org.internship.library.api.BookService;
import org.springframework.boot.SpringApplication;
import org.internship.library.impl.BookServiceImpl;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class LibraryApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibraryApplication.class, args);
	}

	@Bean
	public BookService bookService() {
		return new BookServiceImpl();
	}
}
