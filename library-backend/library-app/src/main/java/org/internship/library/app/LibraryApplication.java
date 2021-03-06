package org.internship.library.app;

import org.internship.library.app.security.CorsConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication(scanBasePackages = "org.internship.library")
@Import({LibraryAppConfig.class, CorsConfig.class})
public class LibraryApplication
{

    public static void main(String[] args)
    {
        SpringApplication.run(LibraryApplication.class, args);
    }

}
