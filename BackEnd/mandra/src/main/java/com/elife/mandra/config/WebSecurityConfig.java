package com.elife.mandra.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.web.SecurityFilterChain;

import com.elife.mandra.Security.CookieBearerTokenResolver;
import com.elife.mandra.Security.CustomAuthenticationEntryPoint;

import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {

    @Value("${jwt.cookie-name}")
    private String jwtCookieName; // Injects the JWT cookie name from the configuration file

    private final AuthenticationProvider authenticationProvider; // Authentication provider
    private final JwtAuthenticationConverter jwtAuthenticationConverter;
    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

    // Define a custom AccessDeniedHandler bean
    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return (request, response, accessDeniedException) -> {
            response.setStatus(HttpStatus.FORBIDDEN.value());
            response.setContentType("application/json");
            response.getWriter().write("{\"message\": \"Access Denied\", \"details\": \"" + accessDeniedException.getMessage() + "\"}");
        };
    }

    // Define a custom AuthenticationEntryPoint bean
    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return new Http403ForbiddenEntryPoint();
    }

    // Defines the SecurityFilterChain bean
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests((requests) -> requests
                // Allows access without authentication to /auth/**, /owners/**, and /clients/** endpoints
                .requestMatchers("/auth/**", "/owners/**", "/clients/**").permitAll()
                // All other requests must be authenticated
                .anyRequest().authenticated()
            )
            // Disables CSRF (Cross-Site Request Forgery) protection
            .csrf(csrf -> csrf.disable())
            // Configures HTTP Basic authentication
            .httpBasic(httpBasic -> httpBasic.authenticationEntryPoint(customAuthenticationEntryPoint))
            // Configures the OAuth2 resource server to use JWT
            .oauth2ResourceServer(oauth2 -> oauth2
                // Uses a token resolver for cookies named jwtCookieName
                .bearerTokenResolver(new CookieBearerTokenResolver(jwtCookieName))
                // Uses the JwtAuthenticationConverter
                .jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter))   
            )
            // Configures session management to be stateless
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            // Configures exception handling for authentication and access denial
            .exceptionHandling(exceptions -> exceptions
                .authenticationEntryPoint(authenticationEntryPoint())
                .accessDeniedHandler(accessDeniedHandler())
            )
            // Configures the custom authentication provider
            .authenticationProvider(authenticationProvider);

        // Builds and returns the SecurityFilterChain object
        return http.build();
    }
}
