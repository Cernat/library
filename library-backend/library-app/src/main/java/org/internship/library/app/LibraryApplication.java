package org.internship.library.app;

import org.internship.library.app.persistence.LibraryPersistenceConfig;
import org.internship.library.app.security.AppConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication(scanBasePackages = "org.internship.library")
@Import({LibraryAppConfig.class, LibraryPersistenceConfig.class, AppConfig.class})
public class LibraryApplication {

    public static void main(String[] args) {
        SpringApplication.run(LibraryApplication.class, args);
    }


}
