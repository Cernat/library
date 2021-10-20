package org.internship.utility.app;

import org.internship.library.client.BookRestClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class UtilityConfig {

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        return  restTemplate;
    }

    @Bean
    public BookRestClient bookRestClient(RestTemplate restTemplate) {
        BookRestClient bookRestClient = new BookRestClient();
        bookRestClient.setRestTemplate(restTemplate);
        return bookRestClient;
    }
}
