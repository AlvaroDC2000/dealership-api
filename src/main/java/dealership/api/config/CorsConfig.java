package dealership.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Spring configuration class responsible for defining CORS settings
 * for the API.
 * <p>
 * This configuration enables Cross-Origin Resource Sharing (CORS)
 * for HTTP requests targeting the API endpoints, allowing controlled
 * access from external client applications.
 * </p>
 */
@Configuration
public class CorsConfig {

    /**
     * Provides a {@link WebMvcConfigurer} bean that customizes CORS mappings.
     * <p>
     * The configured rules apply to all API endpoints matching the defined
     * path pattern and specify the allowed origins, HTTP methods, headers,
     * and credential policy.
     * </p>
     *
     * @return a WebMvcConfigurer instance with custom CORS configuration
     */
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            /**
             * Adds CORS mappings to the application.
             * <p>
             * This method defines which origins are allowed to access the API,
             * which HTTP methods and headers are permitted, and whether
             * credentials are supported.
             * </p>
             *
             * @param registry the CORS registry used to register mappings
             */
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**")
                        .allowedOrigins("http://localhost:4200")
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*")
                        .allowCredentials(false);
            }
        };
    }
}
