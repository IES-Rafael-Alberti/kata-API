package com.example.demo.cors;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuration class for customizing application settings.
 */
@EnableWebMvc
@Configuration
public class MyConfiguration {
        @Bean
        public ModelMapper modelMapper() {
            return new ModelMapper();
        }


        /**
         * Bean definition for customizing CORS (Cross-Origin Resource Sharing) configuration.
         *
         * @return WebMvcConfigurer instance.
         */
        @Bean
        public WebMvcConfigurer corsConfigurer() {

            return new WebMvcConfigurer() {

                /**
                 * Configures CORS mappings for the application.
                 *
                 * @param registry CorsRegistry to configure CORS mappings.
                 */
                @Override
                public void addCorsMappings(CorsRegistry registry) {
                    registry.addMapping("/**")
                            .allowedOrigins("http://localhost:9001")
                            .allowedMethods("GET", "POST", "PUT", "DELETE")
                            .maxAge(3600);
                }
            };
        }
}
