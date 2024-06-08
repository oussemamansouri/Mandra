package com.elife.mandra.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
// @EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable() // Disable CSRF for APIs
            .authorizeHttpRequests((requests) -> requests
                // .requestMatchers("/api/auth/**").permitAll()
                // .anyRequest().authenticated()
                .anyRequest().permitAll() // Allow all paths without authentication
            );
       

        return http.build();
    }

   
}
