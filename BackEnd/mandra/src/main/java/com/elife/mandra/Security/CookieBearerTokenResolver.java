package com.elife.mandra.Security;

import org.springframework.security.oauth2.server.resource.web.BearerTokenResolver;
import org.springframework.util.StringUtils;
import org.springframework.web.util.WebUtils;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

public class CookieBearerTokenResolver implements BearerTokenResolver {

     private final String cookieName; // Name of the cookie containing the token

    // Constructor to initialize with the cookie name
    public CookieBearerTokenResolver(String cookieName) {
        this.cookieName = cookieName;
    }

    // Implementation of the resolve method to extract the token from the cookie
    @Override
    public String resolve(HttpServletRequest request) {
        // Retrieve the cookie by name from the request
        Cookie cookie = WebUtils.getCookie(request, cookieName);
        
        // Check if the cookie exists and has a non-empty value
        if (cookie != null && StringUtils.hasText(cookie.getValue())) {
            // Return the token value from the cookie
            return cookie.getValue();
        }  
        // Return null if the cookie does not exist or is empty
        return null;
    }

}
