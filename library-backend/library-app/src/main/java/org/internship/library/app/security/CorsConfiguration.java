package org.internship.library.app.security;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Configures CORS
 */
//@Configuration
//@ConfigurationProperties(prefix = "cors")
public class CorsConfiguration {
    private static final Log logger = LogFactory.getLog(CorsConfiguration.class);

    private List<String> allowedOrigins = new ArrayList<String>();

    // This is needed to get the allowed origins list from the library.yaml
    public List<String> getAllowedOrigins() {
        return this.allowedOrigins;
    }

    public CorsConfiguration() {

    }

//    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry corsRegistry) {
                // Save the list as an array
                String[] originsArray = new String[allowedOrigins.size()];
                allowedOrigins.toArray(originsArray);

                // Do we have any origins to work with?
                if (originsArray.length > 0) {
                    logger.info("Allowed Origins: " + Arrays.toString(originsArray));
                }
                else {
                    logger.info("No origins provided. Please configure at least one origin.");
                }

                // GET, HEAD, and POST are allowed by default. Any other methods must be declared
                // Mapping should be the exact path or an Ant-style path patterns
                // All headers and credentials are allowed
                // MaxAge is using the default of 30 minutes.
                corsRegistry.addMapping("/**")
                        .allowedOrigins(originsArray)
                        .allowedMethods("POST", "GET", "PUT", "DELETE");
            }
        };
    }
}
