package com.elife.mandra.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer{

    
    @Override
    public void addCorsMappings( CorsRegistry registry) {
     registry.addMapping("/**") // Apply CORS configuration to all endpoints
     .allowedOrigins("http://localhost:4200") // Allow requests from the specified origin
     .allowedMethods("GET", "POST", "PUT", "DELETE","PATCH", "OPTIONS")// Allow specified HTTP methods
     .allowedHeaders("*")// Allow all headers
     .allowCredentials(true); // Allow credentials (e.g., cookies, authorization headers)
    }

}
